package com.preservica.cli.repository;

import com.preservica.cli.model.Student;

import java.util.List;


public interface StudentRepository {
    void save(Student student);

    List<Student> findAll();
}
