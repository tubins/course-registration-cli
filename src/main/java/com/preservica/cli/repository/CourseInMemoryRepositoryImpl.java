package com.preservica.cli.repository;

import com.preservica.cli.model.Course;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CourseInMemoryRepositoryImpl implements CourseRepository {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    private static Map<Integer, Course> courseStore = new HashMap<>();


    @Override
    public void save(Course course) {
        course.setId(atomicInteger.incrementAndGet());
        courseStore.put(course.getId(), course);
    }

    @Override
    public List<Course> findAll() {
        return new ArrayList<>(courseStore.values());
    }

    @Override
    public Optional<Course> findById(Integer id) {
        return Optional.ofNullable(courseStore.get(id));
    }

    @Override
    public void update(Course course) {
        courseStore.put(course.getId(), course);
    }
}
