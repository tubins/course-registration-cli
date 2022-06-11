package com.preservica.cli.dp.command;

import com.preservica.cli.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

public class ListStudentCommand implements Command {

    @Autowired
    private StudentService studentService;

    private final CommandShortCut commandShortCut = CommandShortCut.L;
    private final String message = "List Student";
    private Command backCommand;

    public ListStudentCommand(StudentService studentService) {
        this.studentService =  studentService;
    }


    private void listStudent() {
        System.out.println("Listing students");
        studentService.listAll().forEach(System.out::println);
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
