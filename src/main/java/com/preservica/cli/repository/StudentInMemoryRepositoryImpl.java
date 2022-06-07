package com.preservica.cli.repository;

import com.preservica.cli.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implementation class for @{@link StudentRepository}.
 */
@Service
public class StudentInMemoryRepositoryImpl implements StudentRepository {
    private static final AtomicInteger atomicInteger = new AtomicInteger();
    private static final Map<Integer, Student> studentStore = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(final Student student) {
        student.setId(atomicInteger.incrementAndGet());
        studentStore.put(student.getId(), student);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Student> findAll() {
        return new ArrayList<>(studentStore.values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Student> findById(final Integer id) {
        return Optional.ofNullable(studentStore.get(id));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final Student student) {
        studentStore.put(student.getId(), student);
    }
}
