package com.preservica.cli.repository;

import com.preservica.cli.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    void save(Course course);

    List<Course> findAll();
    Optional<Course> findById(Integer id);

    void update(Course course);
}
