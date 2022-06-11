package com.preservica.cli.dp.command;

import com.preservica.cli.dp.service.ReadUserCommandService;
import com.preservica.cli.service.CourseService;
import lombok.Setter;

@Setter
public class AddStudentCommand implements Command {

    private final ReadUserCommandService readUserCommandService;
    private final CourseService courseService;

    private final CommandShortCut commandShortCut = CommandShortCut.A;
    private final String message = "Add Student";

    private Command backCommand;

    public AddStudentCommand(ReadUserCommandService readUserCommandService, CourseService courseService) {
        this.readUserCommandService = readUserCommandService;
        this.courseService = courseService;
    }

    private void addStudent() {
        System.out.println("Student added");
        backCommand.execute();
    }

    @Override
    public void execute() {
        addStudent();
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
