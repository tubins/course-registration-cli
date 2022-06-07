package com.preservica.cli.controller;

import com.preservica.cli.service.StudentService;
import com.preservica.cli.service.UserInputService;
import org.springframework.stereotype.Component;

@Component
public class StudentsMenuController {
    private final UserInputService userInputService;
    private final StudentService studentService;

    public StudentsMenuController(UserInputService userInputService, StudentService studentService) {
        this.userInputService = userInputService;
        this.studentService = studentService;
    }

    public void run() {
        printMenu();
        String userInput = userInputService.readUserInput();
        switch (userInput) {
            case "n":
            case "N":
                studentService.save();
                break;
            case "l":
            case "L":
                studentService.listAll();
                break;
            case "a":
            case "A":
                studentService.enrollCourse();
                break;
            case "r":
            case "R":
                studentService.removeCourse();
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
                "\nStudents menu\n" +
                        "Please choose an option from the menu by selecting the first letter.\n" +
                        "====== Menu ======\n" +
                        "N. New Students\n" +
                        "L. List Students\n" +
                        "A. Assign Course\n" +
                        "R. Remove Course\n" +
                        "B. Return to main menu\n" +
                        "==================\n" +
                        "Enter here : "
        );
    }
}
