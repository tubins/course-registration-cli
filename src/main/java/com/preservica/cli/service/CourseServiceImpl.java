package com.preservica.cli.service;

import com.preservica.cli.model.Course;
import com.preservica.cli.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void save(final Course course) {
        courseRepository.save(course);
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> findById(final Integer id) {
        return courseRepository.findById(id);
    }

    @Override
    public void update(final Course course) {
        courseRepository.update(course);
    }

    @Override
    public boolean isCourseFullyEnrolled(Course course) {
        if (course == null) {
            return false;
        }
        if (!CollectionUtils.isEmpty(course.getEnrolledStudents())) {
            return (course.getEnrolledStudents().size() + 1) > course.getMaximumClassSize();
        }
        return false;
    }

}
