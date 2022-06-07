package com.preservica.cli.service;

import com.preservica.cli.model.Course;

import java.util.List;
import java.util.Optional;

/** CourseService. */
public interface CourseService {
    void save(Course course);
    List<Course> listAll();
    Optional<Course> findById(Integer id);
    void update(Course course);
     boolean isCourseFullyEnrolled(Course course);
}
