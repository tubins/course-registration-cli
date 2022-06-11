package com.preservica;

import com.preservica.cli.controller.MainMenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CourseRegistrationCliApplication implements CommandLineRunner
{
    @Autowired
    private MainMenuController mainMenuController;

    public static void main(String[] args) {
        SpringApplication.run(CourseRegistrationCliApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
       // mainMenuController.run();
    }
}
