package com.preservica.cli.controller;

import com.preservica.cli.model.Course;
import com.preservica.cli.service.CourseService;
import com.preservica.cli.service.StudentService;
import com.preservica.cli.service.UserInputService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Course Menu Controller.
 */
@Component
public class CourseMenuController {
    @Autowired
    private UserInputService userInputService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;

    /**
     * Menu run method.
     */
    public void run() {
        printMenu();
        String userInput = userInputService.readStringInputFromConsole();
        switch (userInput) {
            case "n":
            case "N":
                save();
                break;
            case "l":
            case "L":
                listAll();
                break;
            case "b":
            case "B":
                return;
            default:
                System.out.println("Please enter correct option! \nEnter here : ");
        }
        run();
    }

    /**
     * Print main menu.
     */
    private void printMenu() {
        System.out.println(
                "\nCourse menu\n" +
                        "Please choose an option from the menu by selecting the first letter.\n" +
                        "====== Menu ======\n" +
                        "N. New Courses\n" +
                        "L. List Courses\n" +
                        "B. Return to main menu\n" +
                        "==================\n" +
                        "Enter here : "
        );
    }

    private void save() {
        System.out.println("Enter course name: ");
        String courseName = userInputService.readStringInputFromConsole();
        if (StringUtils.isBlank(courseName)) {
            System.out.println("Invalid name!");
            return;
        }
        System.out.println("Enter course maximum class size: ");
        Integer maxClassSizeString = userInputService.readIntegerInputFromConsole(5, 100);

        System.out.println("Save course details? : Y/N");
        if (userInputService.readConfirmationFromConsole()) {
            Course course = Course.builder().name(courseName).maximumClassSize(maxClassSizeString).build();
            courseService.save(course);
            System.out.println(String.format("Course information saved successfully : %s", course.getName()));
            return;
        }
        System.out.println("Course information not saved.");
    }

    private void listAll() {
        System.out.println("Listing all courses");
        List<Course> courses = courseService.listAll();
        if (CollectionUtils.isEmpty(courses)) {
            System.out.println("No courses information found!");
            return;
        }
        courses.forEach(course -> {
            System.out.println("=====================");
            System.out.println(course);
            if (!CollectionUtils.isEmpty(course.getEnrolledStudents())) {
                System.out.println("Student enrolled:");
                course.getEnrolledStudents()
                        .stream()
                        .map(studentService::findById)
                        .collect(Collectors.toList())
                        .stream()
                        .forEach(System.out::println);
            }
            System.out.println("=====================");
        });
    }
}
