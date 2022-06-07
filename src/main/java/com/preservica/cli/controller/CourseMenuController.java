package com.preservica.cli.controller;

import com.preservica.cli.service.CourseService;
import com.preservica.cli.service.StudentService;
import com.preservica.cli.service.UserInputService;
import org.springframework.stereotype.Component;

@Component
public class CourseMenuController {
    private final UserInputService userInputService;
    private final CourseService courseService;

    public CourseMenuController(UserInputService userInputService,  CourseService courseService) {
        this.userInputService = userInputService;
        this.courseService = courseService;
    }

    public void run() {
        printMenu();
        String userInput = userInputService.readUserInput();
        switch (userInput) {
            case "n":
            case "N":
                System.out.println("New Course option selected.");
                courseService.save();
                break;
            case "l":
            case "L":
                System.out.println("List course Option selected.");
                courseService.listAll();
                break;
            case "b":
            case "B":
                return;
            default:
                System.out.println("Please enter correct option! \nEnter here : ");
        }
        run();
    }

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
}
