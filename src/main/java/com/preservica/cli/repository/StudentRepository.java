package com.preservica.cli.repository;

import com.preservica.cli.model.Student;

import java.util.List;
import java.util.Optional;

/** StudentRepository. */
public interface StudentRepository {
    /**
     * Save student.
     *
     * @param student student to save.
     */
    void save(Student student);
    /**
     * List all students.
     *
     * @return list of all students.
     */
    List<Student> findAll();
    /**
     * Find a student by its Id.
     *
     * @param id id to match with.
     * @return matching student or else optional null.
     */
    Optional<Student> findById(Integer id);
    /**
     * Update student.
     *
     * @param student student to update.
     */
    void update(Student student);
}
