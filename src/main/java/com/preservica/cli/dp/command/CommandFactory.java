package com.preservica.cli.dp.command;

import com.preservica.cli.dp.service.ReadUserCommandService;
import com.preservica.cli.service.CourseService;
import com.preservica.cli.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommandFactory {
    private static final String MAIN_MENU_NAME = "University Course registration Application";
    private static final String COURSE_MENU_NAME = "Courses";
    private static final String STUDENTS_MENU_NAME = "Students";

    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private ReadUserCommandService readUserCommandService;

    public Command getMainMenuCommand() {
        Command exitCommand = new ExitCommand();
        Command studentsMenuCommand = getStudentsMenuCommand();
        Command courseMenuCommand = getCourseMenuCommand();
        List<Command> mainMenuCommands = new ArrayList<>();
        mainMenuCommands.add(studentsMenuCommand);
        mainMenuCommands.add(courseMenuCommand);
        mainMenuCommands.add(exitCommand);
        ShowMenuCommand showMainMenuCommand = new ShowMenuCommand(
                readUserCommandService, CommandShortCut.M,
                MAIN_MENU_NAME, mainMenuCommands);
        BackCommand backCommand = new BackCommand(showMainMenuCommand);
        showMainMenuCommand.setBackCommand(backCommand);
        studentsMenuCommand.setBackCommand(backCommand);
        courseMenuCommand.setBackCommand(backCommand);
        return showMainMenuCommand;
    }

    private Command getStudentsMenuCommand() {
        AddStudentCommand addStudentCommand = new AddStudentCommand(readUserCommandService, courseService);
        ListStudentCommand listStudentCommand = new ListStudentCommand(studentService);
        List<Command> studentsMenuList = new ArrayList<>();
        studentsMenuList.add(addStudentCommand);
        studentsMenuList.add(listStudentCommand);
        ShowMenuCommand showStudentMenuCommand = new ShowMenuCommand(
                readUserCommandService, CommandShortCut.S,
                STUDENTS_MENU_NAME, studentsMenuList);
        BackCommand backCommand = new BackCommand(showStudentMenuCommand);
        addStudentCommand.setBackCommand(backCommand);
        listStudentCommand.setBackCommand(backCommand);
        showStudentMenuCommand.setBackCommand(backCommand);
        return showStudentMenuCommand;
    }

    private Command getCourseMenuCommand() {
        ListCoursesCommand listCoursesCommand = new ListCoursesCommand(courseService);
        AddCourseCommand addCourseCommand = new AddCourseCommand(readUserCommandService, courseService);
        List<Command> courseMenuList = new ArrayList<>();
        courseMenuList.add(listCoursesCommand);
        courseMenuList.add(addCourseCommand);
        ShowMenuCommand showCourseMenuCommand = new ShowMenuCommand(
                readUserCommandService, CommandShortCut.C,
                COURSE_MENU_NAME, courseMenuList);
        BackCommand backCommand = new BackCommand(showCourseMenuCommand);
        listCoursesCommand.setBackCommand(backCommand);
        addCourseCommand.setBackCommand(backCommand);
        showCourseMenuCommand.setBackCommand(backCommand);
        return showCourseMenuCommand;
    }

}
