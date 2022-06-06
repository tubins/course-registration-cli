package com.preservica.cli.service;

import com.preservica.cli.model.Student;
import com.preservica.cli.repository.StudentRepository;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Random;

@Service
public class StudentServiceImpl implements StudentService {

    private final UserInputService userInputService;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(UserInputService userInputService, StudentRepository studentRepository) {
        this.userInputService = userInputService;
        this.studentRepository = studentRepository;
    }

    @Override
    public void saveStudent() {
        System.out.println("Enter Student name: ");
        String studentName = userInputService.readUserInput();
        if (StringUtils.isBlank(studentName)) {
            System.out.println("Invalid name!");
            return;
        }
        System.out.println("Enter Student age: ");
        String ageString = userInputService.readUserInput();
        if (!isValidAge(ageString)) {
            System.out.println("Invalid age!");
            return;
        }

        System.out.println("Save student details? : Y/N");
        String confirmation = userInputService.readUserInput();

        switch (confirmation) {
            case "Y":
            case "y":
                Student student = Student.builder()
                        .id(new Random().nextInt())
                        .name(studentName)
                        .age(ageString)
                        .build();
                studentRepository.save(student);
                System.out.println(String.format("Student information saved successfully : %s", student.getName()));
                return;
            case "N":
            case "n":
                System.out.println("Student information not saved.");
                return;
            default:
                System.out.println("Invalid option.");
        }

    }

    @Override
    public void listAll() {
        System.out.println("Listing all students");
        List<Student> studentList = studentRepository.findAll();
        if(CollectionUtils.isEmpty(studentList)) {
            System.out.println("No students information found!");
            return;
        }
        studentList.forEach(System.out::println);
    }

    private boolean isValidAge(final String ageString) {
        if (!StringUtils.isNumeric(ageString)) {
            return false;
        }
        int age = NumberUtils.toInt(ageString);
        if (age < 10 || age > 55) {
            return false;
        }
        return true;
    }
}
