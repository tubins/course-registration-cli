package com.preservica.cli.repository;

import com.preservica.cli.model.Course;

import java.util.List;
import java.util.Optional;

/**
 * CourseRepository.
 */
public interface CourseRepository {
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
    List<Course> findAll();

    /**
     * Find a course by its Id.
     *
     * @param id id to match with.
     * @return matching course or else optional null.
     */
    Optional<Course> findById(Integer id);

    /**
     * Update course.
     *
     * @param course course to update.
     */
    void update(Course course);
}
