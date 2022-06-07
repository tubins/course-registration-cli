package com.preservica.cli.service;

import com.preservica.cli.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    void save(Student student);

    List<Student> listAll();

    boolean enrollCourse(Integer studentId, Integer courseId);

    boolean removeCourse(Integer studentId, Integer courseId);

    Optional<Student> findById(Integer id);

}
