package com.preservica.cli.controller;

import com.preservica.cli.service.UserInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * MainMenuController.
 */
@Component
@Order(2)
public class MainMenuController implements CommandLineRunner {
    @Autowired
    private UserInputService userInputService;
    @Autowired
    private StudentsMenuController studentsMenuController;
    @Autowired
    private CourseMenuController courseMenuController;

    /**
     * Menu run method.
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("Welcome to University registration Application\n");
        printMenu();
        String userInput = userInputService.readStringInputFromConsole();
        switch (userInput) {
            case "s":
            case "S":
                studentsMenuController.run();
                break;
            case "t":
            case "T":
                System.out.println("Teachers option selected. Feature not implemented!");
                break;
            case "c":
            case "C":
                courseMenuController.run();
                break;
            case "e":
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
                "Please choose an option from the menu by selecting the first letter.\n" +
                        "====== Menu ======\n" +
                        "S. Students\n" +
                        "T. Teachers\n" +
                        "C. Courses\n" +
                        "E. Exit\n" +
                        "==================\n" +
                        "Enter here : "
        );
    }
}
