package com.preservica.cli.repository;

import com.preservica.cli.model.Student;

import java.util.List;
import java.util.Optional;


public interface StudentRepository {
    void save(Student student);

    List<Student> findAll();
    Optional<Student> findById(Integer id);
    void update(Student student);
}
