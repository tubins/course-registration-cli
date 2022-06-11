package com.preservica.cli.dp.command;

import com.preservica.cli.dp.service.ReadUserCommandService;
import com.preservica.cli.model.Course;
import com.preservica.cli.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddCourseCommand implements Command {
    private final String message = "Add Course";
    private final CommandShortCut commandShortCut = CommandShortCut.A;
    private final ReadUserCommandService readUserCommandService;

    private final CourseService courseService;

    private Command backCommand;

    private void addStudent() {
        System.out.println("Courses added");
        System.out.println("Enter course name");
        String courseName = readUserCommandService.readStringInputFromConsole();
        Course.CourseBuilder builder = Course.builder();
        builder.name(courseName);
        System.out.println("Enter max class size" );
        Integer integer = readUserCommandService.readIntegerInputFromConsole(2, 10);
        builder.maximumClassSize(integer);
        courseService.save(builder.build());
        System.out.println("Course details saved - " + courseName);
        backCommand.execute();
    }

    @Override
    public void execute() {
        addStudent();
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
