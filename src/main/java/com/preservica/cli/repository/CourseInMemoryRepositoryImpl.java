package com.preservica.cli.repository;

import com.preservica.cli.model.Course;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/** Implementation class for @{@link CourseRepository}. */
@Service
public class CourseInMemoryRepositoryImpl implements CourseRepository {
    private static final AtomicInteger atomicInteger = new AtomicInteger();
    private static final Map<Integer, Course> courseStore = new HashMap<>();

    /** {@inheritDoc} */
    @Override
    public void save(final Course course) {
        course.setId(atomicInteger.incrementAndGet());
        courseStore.put(course.getId(), course);
    }
    /** {@inheritDoc} */
    @Override
    public List<Course> findAll() {
        return new ArrayList<>(courseStore.values());
    }
    /** {@inheritDoc} */
    @Override
    public Optional<Course> findById(final Integer id) {
        return Optional.ofNullable(courseStore.get(id));
    }
    /** {@inheritDoc} */
    @Override
    public void update(final Course course) {
        courseStore.put(course.getId(), course);
    }
}
