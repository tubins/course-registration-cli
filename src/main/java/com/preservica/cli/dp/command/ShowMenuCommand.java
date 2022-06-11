package com.preservica.cli.dp.command;


import com.preservica.cli.dp.service.ReadUserCommandService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ShowMenuCommand implements Command {
    private final ReadUserCommandService readUserCommandService;
    private final CommandShortCut commandShortCut;
    private final String menuName;
    private final List<Command> commandList;

    private Command backCommand;

    @Override
    public void execute() {
        setBackCommand();
        showMenu();
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
        return menuName;
    }


    public void showMenu() {
        System.out.println(getDescription());
        System.out.println("Please choose an option with the shortcut letter.\n");
        int i = 1;
        for (Command c : commandList) {
            System.out.println(i + ")" + c.getShortCut() + " : " + c.getDescription());
            i++;
        }
        Command command = readUserCommandService.readCommand(this.commandList);
        command.execute();
        this.backCommand.execute();
    }

    private void setBackCommand() {
        if (!this.commandList.contains(backCommand) && this.commandList
                .stream()
                .noneMatch(command -> command.getShortCut().equals(CommandShortCut.Q))) {
            this.commandList.add(backCommand);
        }
    }
}
