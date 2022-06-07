package com.preservica.cli.controller;

import com.preservica.cli.model.Course;
import com.preservica.cli.model.Student;
import com.preservica.cli.service.CourseService;
import com.preservica.cli.service.StudentService;
import com.preservica.cli.service.UserInputService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * StudentsMenuController.
 */
@Component
public class StudentsMenuController {
    @Autowired
    private UserInputService userInputService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    /**
     * Menu run method.
     */
    public void run() {
        printMenu();
        String userInput = userInputService.readStringInputFromConsole();
        switch (userInput) {
            case "n":
            case "N":
                save();
                break;
            case "l":
            case "L":
                listAll();
                break;
            case "a":
            case "A":
                enrollCourse();
                break;
            case "r":
            case "R":
                removeCourse();
                break;
            case "b":
            case "B":
                return;
            default:
                System.out.println("Please enter correct option! \nEnter here : ");
        }
        run();
    }

    /**
     * Print main menu.
     */
    private void printMenu() {
        System.out.println(
                "\nStudents menu\n" +
                        "Please choose an option from the menu by selecting the first letter.\n" +
                        "====== Menu ======\n" +
                        "N. New Students\n" +
                        "L. List Students\n" +
                        "A. Assign Course\n" +
                        "R. Remove Course\n" +
                        "B. Return to main menu\n" +
                        "==================\n" +
                        "Enter here : "
        );
    }

    private void save() {
        System.out.println("Enter Student name: ");
        String studentName = userInputService.readStringInputFromConsole();
        if (StringUtils.isBlank(studentName)) {
            System.out.println("Invalid name!");
            return;
        }
        System.out.println("Enter Student age: ");
        Integer age = userInputService.readIntegerInputFromConsole(18, 45);

        System.out.println("Save student details? : Y/N");
        boolean canSave = userInputService.readConfirmationFromConsole();
        if (canSave) {
            Student student = Student.builder()
                    .name(studentName)
                    .age(Integer.valueOf(age))
                    .build();
            studentService.save(student);
            System.out.println(String.format("Student information saved successfully : %s", student.getName()));
            return;
        }
        System.out.println("Student information not saved.");
    }

    private void listAll() {
        System.out.println("Listing all students");
        List<Student> studentList = studentService.listAll();
        if (CollectionUtils.isEmpty(studentList)) {
            System.out.println("No students information found!");
            return;
        }
        studentList.forEach(System.out::println);
    }

    private void enrollCourse() {
        System.out.println("Enter Student id: ");
        Integer studentId = userInputService.readIntegerInputFromConsole();
        Optional<Student> optionalStudent = studentService.findById(studentId);
        if (optionalStudent.isEmpty()) {
            System.out.println("Student id not found!");
            return;
        }
        System.out.println("Enter course id: ");
        Integer courseId = userInputService.readIntegerInputFromConsole();
        Optional<Course> optionalCourse = courseService.findById(courseId);
        if (optionalCourse.isEmpty()) {
            System.out.println("Course id not found!");
            return;
        }

        Course course = optionalCourse.get();
        if (courseService.isCourseFullyEnrolled(course)) {
            System.out.println(String.format("Maximum students enrolled for the course - %s!", course.getName()));
            return;
        }
        Student student = optionalStudent.get();
        boolean enrolled = studentService.enrollCourse(student.getId(), course.getId());
        if (enrolled) {
            System.out.println(String.format("Student %s is enrolled for the course %s successfully!", student.getName(), course.getName()));
            return;
        }
        System.out.println(String.format("Student %s is not enrolled for the course %s!", student.getName(), course.getName()));
    }


    private void removeCourse() {
        Optional<Student> optionalStudent = readStudentIdGetDetails();
        if (optionalStudent.isEmpty()) {
            System.out.println("Student id not found!");
            return;
        }
        Student student = optionalStudent.get();
        if (student.getCourseId() == null) {
            System.out.println("Student not enrolled for any courses!");
            return;
        }
        Optional<Course> optionalCourse = readCourseIdGetDetails();
        if (optionalCourse.isEmpty()) {
            System.out.println("Course id not found!");
            return;
        }
        Course course = optionalCourse.get();
        boolean courseRemoved = studentService.removeCourse(student.getId(), course.getId());
        if (courseRemoved) {
            System.out.println(String.format("Student %s is removed from the course %s successfully!", student.getName(), course.getName()));
            return;
        }
        System.out.println(String.format("Student %s is not removed from the course %s!", student.getName(), course.getName()));
    }

    private Optional<Student> readStudentIdGetDetails() {
        System.out.println("Enter Student id: ");
        Integer studentId = userInputService.readIntegerInputFromConsole();
        return studentService.findById(studentId);
    }

    private Optional<Course> readCourseIdGetDetails() {
        System.out.println("Enter Student id: ");
        Integer courseId = userInputService.readIntegerInputFromConsole();
        return courseService.findById(courseId);
    }

}
