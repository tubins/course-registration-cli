package com.preservica.cli.service;

import com.preservica.cli.model.Course;

import java.util.List;
import java.util.Optional;

/**
 * CourseService.
 */
public interface CourseService {
    /**
     * Save course.
     *
     * @param course course to save.
     */
    void save(Course course);

    /**
     * List all courses.
     *
     * @return list of all courses.
     */
    List<Course> listAll();

    /**
     * Find course by id.
     *
     * @param id id to match with.
     * @return matching course.
     */
    Optional<Course> findById(Integer id);

    /**
     * Update a course information.
     *
     * @param course course to update.
     */
    void update(Course course);

    /**
     * Is course fully enrolled.
     *
     * @param course course to check.
     * @return true if course fully enrolled; else false.
     */
    boolean isCourseFullyEnrolled(Course course);
}
