package com.preservica.cli.controller;

import com.preservica.cli.model.Student;
import com.preservica.cli.service.UserInputService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class MainMenuController implements CommandLineRunner {

    private final UserInputService userInputService;
    private final StudentsMenuController studentsMenuController;
    private final CourseMenuController courseMenuController;

    public MainMenuController(UserInputService userInputService, StudentsMenuController studentsMenuController, CourseMenuController courseMenuController) {
        this.userInputService = userInputService;
        this.studentsMenuController = studentsMenuController;
        this.courseMenuController = courseMenuController;
    }

    @Override
    public void run(String... args) throws Exception {
        printMenu();
        String userInput = userInputService.readUserInput();
        switch (userInput) {
            case "s":
            case "S":
                System.out.println("Students option selected.");
                studentsMenuController.run();
                break;
            case "t":
            case "T":
                System.out.println("Teachers option selected.");
                break;
            case "c":
            case "C":
                System.out.println("Courses option selected.");
                courseMenuController.run();
                break;
            case "e":
            case "E":
                System.out.println("Saving data");
                System.out.println("Application end successfully.");
                System.exit(0);
            default:
                System.out.println("Please enter correct option! \nEnter here : ");
        }
        run();
    }

    private void printMenu() {
        System.out.println(
                "Welcome to University registration Application\n" +
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
