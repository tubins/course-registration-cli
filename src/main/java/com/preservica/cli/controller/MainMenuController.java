package com.preservica.cli.controller;

import com.preservica.cli.service.UserInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MainMenuController.
 */
@Component
public class MainMenuController {
    @Autowired
    private UserInputService userInputService;
    @Autowired
    private StudentsMenuController studentsMenuController;
    @Autowired
    private CourseMenuController courseMenuController;

    /**
     * Menu run method.
     */
    public void run() {
        System.out.println("Welcome to University registration Application\n");
        printMenu();
        String userInput = userInputService.readStringInputFromConsole();
        switch (userInput.toUpperCase()) {
            case "S":
                studentsMenuController.run();
                break;
            case "T":
                System.out.println("Teachers option selected. Feature not implemented!");
                break;
            case "C":
                courseMenuController.run();
                break;
            case "E":
                System.out.println("Closing application....");
                System.exit(0);
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
                "\nMain menu\n" +
                        "Please choose an option from the menu by selecting the first letter.\n" +
                        "S. Students\n" +
                        "T. Teachers\n" +
                        "C. Courses\n" +
                        "E. Exit\n" +
                        "==================\n" +
                        "Enter here : "
        );
    }
}
