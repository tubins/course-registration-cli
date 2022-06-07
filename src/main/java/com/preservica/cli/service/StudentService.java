package com.preservica.cli.service;

import com.preservica.cli.model.Student;

import java.util.List;
import java.util.Optional;

/**
 * Student Service.
 */
public interface StudentService {
    /**
     * Save new student.
     *
     * @param student student to save.
     */
    void save(Student student);

    /**
     * List all the students.
     *
     * @return lisf of all students.
     */
    List<Student> listAll();

    /**
     * Enroll a student to a course.
     *
     * @param studentId student id add.
     * @param courseId  course id to enroll.
     * @return true if successfully enrolled; else false.
     */
    boolean enrollCourse(Integer studentId, Integer courseId);

    /**
     * Remove a student from course.
     *
     * @param studentId student id.
     * @param courseId  course id.
     * @return return true if removed; else false
     */
    boolean removeCourse(Integer studentId, Integer courseId);

    /**
     * Find use by id.
     *
     * @param id Id to match with.
     * @return student information.
     */
    Optional<Student> findById(Integer id);

}
