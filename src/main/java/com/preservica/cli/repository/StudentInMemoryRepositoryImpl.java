package com.preservica.cli.repository;

import com.preservica.cli.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentInMemoryRepositoryImpl implements StudentRepository {

    private static List<Student> studentList = new ArrayList<>();

    @Override
    public void save(Student student) {
        studentList.add(student);
    }

    @Override
    public List<Student> findAll() {
        return studentList;
    }
}
