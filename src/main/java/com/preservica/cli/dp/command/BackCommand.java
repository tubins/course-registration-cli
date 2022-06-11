package com.preservica.cli.dp.command;


public class BackCommand implements Command {

    private Command backCommand;
    private final CommandShortCut commandShortCut = CommandShortCut.B;
    private final String message = "Back";

    public BackCommand(Command backCommand) {
        this.backCommand = backCommand;
    }

    private void back() {
        backCommand.execute();
    }

    @Override
    public void execute() {
        back();
    }

    @Override
    public void setBackCommand(Command backCommand) {
        this.backCommand = backCommand;
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
