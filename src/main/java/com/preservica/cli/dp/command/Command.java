package com.preservica.cli.dp.command;

public interface Command {
    void execute();
    void setBackCommand(Command backCommand);
    CommandShortCut getShortCut();
    String getDescription();
}
