package com.preservica.cli.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Scanner;

/**
 * Implementation class for user input service.
 */
@Service
public class UserInputServiceImpl implements UserInputService {
    private final Scanner scanner;
    /** {@inheritDoc} */
    public UserInputServiceImpl() {
        this.scanner = new Scanner(System.in);
    }
    /** {@inheritDoc} */
    private String readUserInput() {
        return scanner.nextLine();
    }
    /** {@inheritDoc} */
    @Override
    public boolean readConfirmationFromConsole() {
        String confirmation = readUserInput();
        switch (confirmation.toUpperCase()) {
            case "Y":
                return true;
            case "N":
                return false;
            default:
                System.out.println("Invalid option.");
        }
        return readConfirmationFromConsole();
    }

    @Override
    public String readStringInputFromConsole() {
        String userInput = readUserInput();
        if (StringUtils.isNotBlank(userInput)) {
            return userInput;
        }
        System.out.println("Invalid input!");
        return readStringInputFromConsole();
    }
    /** {@inheritDoc} */
    @Override
    public Integer readIntegerInputFromConsole() {
        String userInput = readUserInput();
        try {
            return Integer.parseInt(userInput);
        } catch (NumberFormatException exception) {
            System.out.println("Invalid input!");
        }
        return readIntegerInputFromConsole();
    }
    /** {@inheritDoc} */
    @Override
    public Integer readIntegerInputFromConsole(final Integer minRange, final Integer maxRange) {
        System.out.println(String.format("Please enter an integer value between %s and %s", minRange, maxRange));
        int intValue = readIntegerInputFromConsole();
        if (intValue >= minRange && intValue <= maxRange) {
            return intValue;
        }
        System.out.println(String.format("Invalid value! Allowed values in range of %s and %s", minRange, maxRange));
        return readIntegerInputFromConsole(minRange, maxRange);
    }
}
