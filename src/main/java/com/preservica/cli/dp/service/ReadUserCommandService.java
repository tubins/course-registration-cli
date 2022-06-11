package com.preservica.cli.dp.service;

import com.preservica.cli.dp.command.Command;

import java.util.List;

public interface ReadUserCommandService {
    boolean readConfirmationFromConsole();

    String readStringInputFromConsole();

    Integer readIntegerInputFromConsole();

    Integer readIntegerInputFromConsole(Integer minRange, Integer maxRange);
    Command readCommand(List<Command> commands);

}
