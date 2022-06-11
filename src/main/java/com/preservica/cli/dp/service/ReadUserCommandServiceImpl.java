package com.preservica.cli.dp.service;

import com.preservica.cli.dp.command.Command;
import com.preservica.cli.dp.command.CommandShortCut;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ReadUserCommandServiceImpl implements ReadUserCommandService {
    private final Scanner scanner;

    public ReadUserCommandServiceImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Command readCommand(final List<Command> commands) throws RuntimeException {
        System.out.println("Please enter command : \n");
        String cmdStr = scanner.nextLine();
        cmdStr = cmdStr.toUpperCase();
        if (!EnumUtils.isValidEnum(CommandShortCut.class, cmdStr)) {
            System.out.println("Invalid command");
            return readCommand(commands);
        }
        CommandShortCut commandShortCut = CommandShortCut.valueOf(cmdStr);
        Optional<Command> optionalCommand = commands
                .stream().
                filter(command -> command.getShortCut().equals(commandShortCut))
                .findFirst();
        if (optionalCommand.isEmpty()) {
            System.out.println("Invalid command");
            return readCommand(commands);
        }
        return optionalCommand.get();
    }

    /**
     * {@inheritDoc}
     */
    private String readUserInput() {
        return scanner.nextLine();
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
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
