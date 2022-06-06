package com.preservica.cli.controller;

import com.preservica.cli.service.UserInputService;
import org.springframework.stereotype.Component;

@Component
public class StudentsMenuController {
    private final UserInputService userInputService;

    public StudentsMenuController(UserInputService userInputService) {
        this.userInputService = userInputService;
    }

    public void run() {
        printMenu();
        String userInput = userInputService.readUserInput();
        switch (userInput) {
            case "n":
            case "N":
                System.out.println("New Students option selected.");
                break;
            case "l":
            case "L":
                System.out.println("List Students Option selected.");
                break;
            case "a":
            case "A":
                System.out.println("Assign Courses option selected.");
                break;
            case "r":
            case "R":
                System.out.println("Remove Courses option selected.");
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
