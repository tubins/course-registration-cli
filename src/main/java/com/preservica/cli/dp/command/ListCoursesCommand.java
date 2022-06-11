package com.preservica.cli.dp.command;

import com.preservica.cli.service.CourseService;

public class ListCoursesCommand implements Command {
    private final CourseService courseService;

    private final CommandShortCut commandShortCut = CommandShortCut.L;
    private final String message = "List Course";

    private Command backCommand;

    public ListCoursesCommand(CourseService courseService) {
        this.courseService = courseService;
    }


    private void listStudent() {
        System.out.println("Listing Courses");
        courseService.listAll().forEach(System.out::println);
        backCommand.execute();
    }

    @Override
    public void execute() {
        listStudent();
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
