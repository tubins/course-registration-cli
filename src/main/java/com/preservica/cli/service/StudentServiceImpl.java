package com.preservica.cli.service;

import com.preservica.cli.model.Course;
import com.preservica.cli.model.Student;
import com.preservica.cli.repository.CourseRepository;
import com.preservica.cli.repository.StudentRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final UserInputService userInputService;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentServiceImpl(UserInputService userInputService, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.userInputService = userInputService;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public void save() {
        System.out.println("Enter Student name: ");
        String studentName = userInputService.readUserInput();
        if (StringUtils.isBlank(studentName)) {
            System.out.println("Invalid name!");
            return;
        }
        System.out.println("Enter Student age: ");
        String ageString = userInputService.readUserInput();
        if (!isValidAge(ageString)) {
            System.out.println("Invalid age!");
            return;
        }

        System.out.println("Save student details? : Y/N");
        String confirmation = userInputService.readUserInput();

        switch (confirmation) {
            case "Y":
            case "y":
                Student student = Student.builder()
                        .name(studentName)
                        .age(Integer.valueOf(ageString))
                        .build();
                studentRepository.save(student);
                System.out.println(String.format("Student information saved successfully : %s", student.getName()));
                return;
            case "N":
            case "n":
                System.out.println("Student information not saved.");
                return;
            default:
                System.out.println("Invalid option.");
        }

    }

    @Override
    public void listAll() {
        System.out.println("Listing all students");
        List<Student> studentList = studentRepository.findAll();
        if (CollectionUtils.isEmpty(studentList)) {
            System.out.println("No students information found!");
            return;
        }
        studentList.forEach(System.out::println);
    }

    @Override
    public void enrollCourse() {
        System.out.println("Enter Student id: ");
        String studentIdStr = userInputService.readUserInput();
        if (StringUtils.isBlank(studentIdStr)) {
            System.out.println("Invalid student id!");
            return;
        }
        Optional<Student> optionalStudent = studentRepository.findById(Integer.valueOf(studentIdStr));
        if (optionalStudent.isEmpty()) {
            System.out.println("Student id not found!");
            return;
        }

        System.out.println("Enter course id: ");
        String courseIdStr = userInputService.readUserInput();
        if (StringUtils.isBlank(courseIdStr)) {
            System.out.println("Invalid course id!");
            return;
        }
        Optional<Course> optionalCourse = courseRepository.findById(Integer.valueOf(courseIdStr));
        if (optionalCourse.isEmpty()) {
            System.out.println("Course id not found!");
            return;
        }

        Course course = optionalCourse.get();
        if (course.isCourseFullyEnrolled()) {
            System.out.println(String.format("Maximum students enrolled for the course - %s!", course.getName()));
            return;
        }
        Student student = optionalStudent.get();
        student.setCourseId(course.getId());
        course.getEnrolledStudents().add(student.getId());
        studentRepository.update(student);
        courseRepository.update(course);
        System.out.println(String.format("Student %s is enrolled for the course %s successfully!", student.getName(), course.getName()));
    }

    @Override
    public void removeCourse() {
        System.out.println("Enter Student id: ");
        String studentIdStr = userInputService.readUserInput();
        if (StringUtils.isBlank(studentIdStr)) {
            System.out.println("Invalid student id!");
            return;
        }
        Optional<Student> optionalStudent = studentRepository.findById(Integer.valueOf(studentIdStr));
        if (optionalStudent.isEmpty()) {
            System.out.println("Student id not found!");
            return;
        }

        System.out.println("Enter course id: ");
        String courseIdStr = userInputService.readUserInput();
        if (StringUtils.isBlank(courseIdStr)) {
            System.out.println("Invalid course id!");
            return;
        }
        Optional<Course> optionalCourse = courseRepository.findById(Integer.valueOf(courseIdStr));
        if (optionalCourse.isEmpty()) {
            System.out.println("Course id not found!");
            return;
        }
        Course course = optionalCourse.get();
        Student student = optionalStudent.get();
        student.setCourseId(null);
        course.getEnrolledStudents().remove(student.getId());
        studentRepository.update(student);
        courseRepository.update(course);
        System.out.println(String.format("Student %s is removed from the course %s successfully!", student.getName(), course.getName()));

    }

    private boolean isValidAge(final String ageString) {
        if (!StringUtils.isNumeric(ageString)) {
            return false;
        }
        int age = NumberUtils.toInt(ageString);
        if (age < 10 || age > 55) {
            return false;
        }
        return true;
    }
}
