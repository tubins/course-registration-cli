package com.preservica.cli.repository;

import com.preservica.cli.model.Student;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentInMemoryRepositoryImpl implements StudentRepository {
    private static AtomicInteger atomicInteger = new AtomicInteger();
    private static Map<Integer, Student> studentStore = new HashMap<>();

    @Override
    public void save(Student student) {
        student.setId(atomicInteger.incrementAndGet());
        studentStore.put(student.getId(), student);
    }

    @Override
    public List<Student> findAll() {
        return new ArrayList<>(studentStore.values());
    }

    @Override
    public Optional<Student> findById(Integer id) {
        return Optional.ofNullable(studentStore.get(id));
    }

    @Override
    public void update(Student student) {
        studentStore.put(student.getId(), student);
    }
}
