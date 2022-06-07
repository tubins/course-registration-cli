package com.preservica.cli.service;

import com.preservica.cli.model.Course;
import com.preservica.cli.repository.CourseRepository;
import com.preservica.cli.repository.StudentRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final UserInputService userInputService;
    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;

    public CourseServiceImpl(UserInputService userInputService, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.userInputService = userInputService;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void save() {
        System.out.println("Enter course name: ");
        String courseName = userInputService.readUserInput();
        if (StringUtils.isBlank(courseName)) {
            System.out.println("Invalid name!");
            return;
        }
        System.out.println("Enter course maximum class size: ");
        String maxClassSizeString = userInputService.readUserInput();
        if (!isValidClassSize(maxClassSizeString)) {
            System.out.println("Invalid maximum class size!");
            return;
        }

        System.out.println("Save course details? : Y/N");
        String confirmation = userInputService.readUserInput();

        switch (confirmation) {
            case "Y":
            case "y":
                Course course = Course.builder().name(courseName).maximumClassSize(Integer.valueOf(maxClassSizeString)).build();
                courseRepository.save(course);
                System.out.println(String.format("Course information saved successfully : %s", course.getName()));
                return;
            case "N":
            case "n":
                System.out.println("Course information not saved.");
                return;
            default:
                System.out.println("Invalid option.");
        }

    }

    @Override
    public void listAll() {
        System.out.println("Listing all courses");
        List<Course> studentList = courseRepository.findAll();
        if (CollectionUtils.isEmpty(studentList)) {
            System.out.println("No courses information found!");
            return;
        }
        studentList.forEach(course -> {
            System.out.println("=====================");
            System.out.println(course);
            System.out.println("Student enrolled:");
            course.getEnrolledStudents()
                    .stream()
                    .map(studentRepository::findById)
                    .collect(Collectors.toList())
                    .stream()
                    .forEach(System.out::println);
            System.out.println("=====================");
        });
    }

    private boolean isValidClassSize(final String ageString) {
        if (!StringUtils.isNumeric(ageString)) {
            return false;
        }
        int age = NumberUtils.toInt(ageString);
        if (age < 10 || age > 100) {
            return false;
        }
        return true;
    }
}
