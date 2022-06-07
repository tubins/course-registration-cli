package com.preservica.cli.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.preservica.cli.model.Course;
import com.preservica.cli.model.DataStore;
import com.preservica.cli.model.Student;
import com.preservica.cli.repository.CourseRepository;
import com.preservica.cli.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileSystemUtils;

import javax.annotation.PreDestroy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Manage loading and saving of inmemory data.
 */
@Component
@Order(1)
public class DataStoreManager implements CommandLineRunner {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Value("${data.store.file.name}")
    private String storeFileName;

    /**
     * {@inheritDoc}
     */
    @Override
    public void run(String... args) throws Exception {

        System.out.println("Loading data.");
        ObjectMapper mapper = new ObjectMapper();
        URL url = getClass().getClassLoader().getResource(storeFileName);
        File storeFile = new File(url.getFile());

        BufferedReader bufferedReader = new BufferedReader(new FileReader(storeFile));
        if (bufferedReader.readLine() == null) {
            loadInitialData();
            bufferedReader.close();
            return;
        }

        DataStore dataStore = mapper.readValue(storeFile, DataStore.class);
        if (CollectionUtils.isEmpty(dataStore.getStudents()) && CollectionUtils.isEmpty(dataStore.getCourses())) {
            loadInitialData();
        } else {
            dataStore.getCourses().forEach(courseRepository::save);
            dataStore.getStudents().forEach(studentRepository::save);
        }
        System.out.println("Loading data completed.");

    }

    /**
     * Save data before on exit.
     */
    @PreDestroy
    private void saveData() throws IOException {
        System.out.println("Saving data.");
        ObjectMapper mapper = new ObjectMapper();
        URL url = getClass().getClassLoader().getResource(storeFileName);
        File storeFile = new File(url.getFile());
        FileSystemUtils.deleteRecursively(storeFile);
        DataStore dataStore = new DataStore();
        dataStore.setCourses(courseRepository.findAll());
        dataStore.setStudents(studentRepository.findAll());
        File file = new File(url.getFile());
        mapper.writeValue(file, dataStore);
        System.out.println("Data saved successfully.");
        System.out.println("Application stopped successfully.");
    }

    private void loadInitialData() {
        Student s1 = Student.builder().id(1).name("S1").age(23).courseId(1).build();
        Student s2 = Student.builder().id(2).name("S2").age(25).courseId(1).build();
        Student s3 = Student.builder().id(3).name("S3").age(26).courseId(1).build();
        Student s4 = Student.builder().id(4).name("S4").age(27).build();
        Student s5 = Student.builder().id(5).name("S5").age(28).build();
        studentRepository.save(s1);
        studentRepository.save(s2);
        studentRepository.save(s3);
        studentRepository.save(s4);
        studentRepository.save(s5);
        ArrayList<Integer> enrolledIds = new ArrayList<>(List.of(1, 2, 3));
        Course course = Course.builder().id(1).name("Science").maximumClassSize(3).enrolledStudents(enrolledIds).build();
        courseRepository.save(course);
    }
}
