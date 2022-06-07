package com.preservica.cli.service;

import com.preservica.cli.model.Course;
import com.preservica.cli.model.Student;
import com.preservica.cli.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseService courseService;
    /** {@inheritDoc} */
    @Override
    public void save(final Student student) {
        studentRepository.save(student);
    }
    /** {@inheritDoc} */
    @Override
    public List<Student> listAll() {
        return studentRepository.findAll();
    }
    /** {@inheritDoc} */
    @Override
    public boolean enrollCourse(final Integer studentId, final Integer courseId) {
        Optional<Student> optionalStudent = findById(studentId);
        if (optionalStudent.isEmpty()) {
            return false;
        }
        Optional<Course> optionalCourse = courseService.findById(courseId);
        if (optionalCourse.isEmpty()) {
            return false;
        }
        Student student = optionalStudent.get();
        Course course = optionalCourse.get();
        student.setCourseId(course.getId());
        if (CollectionUtils.isEmpty(course.getEnrolledStudents())) {
            course.setEnrolledStudents(new ArrayList<>());
        }
        if(courseService.isCourseFullyEnrolled(course)) {
            return false;
        }
        course.getEnrolledStudents().add(student.getId());
        studentRepository.update(student);
        courseService.update(course);
        return true;
    }
    /** {@inheritDoc} */
    @Override
    public boolean removeCourse(final Integer studentId, final Integer courseId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isEmpty()) {
            return false;
        }
        Student student = optionalStudent.get();
        if (student.getCourseId() == null) {
            return false;
        }

        Optional<Course> optionalCourse = courseService.findById(courseId);
        if (optionalCourse.isEmpty()) {
            return false;
        }
        Course course = optionalCourse.get();
        if (CollectionUtils.isEmpty(course.getEnrolledStudents())) {
            return false;
        }
        student.setCourseId(null);
        course.getEnrolledStudents().remove(student.getId());
        studentRepository.update(student);
        courseService.update(course);
        return true;
    }
    /** {@inheritDoc} */
    @Override
    public Optional<Student> findById(final Integer id) {
        return studentRepository.findById(id);
    }

}
