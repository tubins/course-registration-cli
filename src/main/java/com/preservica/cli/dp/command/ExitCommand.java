package com.preservica.cli.dp.command;


public class ExitCommand implements Command{

    private final CommandShortCut commandShortCut = CommandShortCut.Q;
    private final String message = "Exit application";

    private void exitApplication() {
        System.out.println("Closing application.");
        System.exit(0);
    }

    @Override
    public void execute() {
       exitApplication();
    }

    @Override
    public void setBackCommand(Command backCommand) {
        // Do nothing
    }

    @Override
    public CommandShortCut getShortCut() {
        return commandShortCut;
    }

    @Override
    public String getDescription() {
        return message;
    }

}
