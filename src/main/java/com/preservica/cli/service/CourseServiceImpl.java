package com.preservica.cli.service;

import com.preservica.cli.model.Course;
import com.preservica.cli.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * Implementation class for @{@link CourseService}.
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    /** {@inheritDoc} */
    @Override
    public void save(final Course course) {
        courseRepository.save(course);
    }
    /** {@inheritDoc} */
    @Override
    public List<Course> listAll() {
        return courseRepository.findAll();
    }
    /** {@inheritDoc} */
    @Override
    public Optional<Course> findById(final Integer id) {
        return courseRepository.findById(id);
    }
    /** {@inheritDoc} */
    @Override
    public void update(final Course course) {
        courseRepository.update(course);
    }
    /** {@inheritDoc} */
    @Override
    public boolean isCourseFullyEnrolled(final Course course) {
        if (course == null) {
            return false;
        }
        if (!CollectionUtils.isEmpty(course.getEnrolledStudents())) {
            return (course.getEnrolledStudents().size() + 1) > course.getMaximumClassSize();
        }
        return false;
    }
}
