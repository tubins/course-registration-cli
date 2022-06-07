package com.preservica.cli.service;

import ch.qos.logback.classic.spi.EventArgUtil;
import com.preservica.cli.model.Course;
import com.preservica.cli.model.Student;
import com.preservica.cli.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test of @{@link StudentServiceImpl} class.
 */
@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseService courseService;

    @Mock
    private Student mockStudent;

    @Mock
    private Course mockCourse;

    @Test
    void save() {
        studentService.save(mockStudent);
        verify(studentRepository).save(mockStudent);
    }

    @Test
    void listAll() {
        when(studentRepository.findAll()).thenReturn(Collections.singletonList(mockStudent));
        List<Student> students = studentService.listAll();
        assertNotNull(students);
        assertEquals(mockStudent, students.get(0));
        verify(studentRepository).findAll();
    }

    @Test
    void enrollCourseShouldReturnFalseIfStudentInfoNotFound() {
        Integer studentId = 1;
        Integer courseId = 1;
        when(studentRepository.findById(any())).thenReturn(Optional.empty());
        boolean enrolledStatus = studentService.enrollCourse(studentId, courseId);
        assertFalse(enrolledStatus);
        verify(studentRepository).findById(studentId);
    }

    @Test
    void enrollCourseShouldReturnFalseIfCourseInfoNotFound() {
        Integer studentId = 1;
        mockStudent.setCourseId(studentId);
        Integer courseId = 1;
        mockCourse.setId(courseId);
        when(studentRepository.findById(any())).thenReturn(Optional.of(mockStudent));
        when(courseService.findById(any())).thenReturn(Optional.empty());
        boolean enrolledStatus = studentService.enrollCourse(studentId, courseId);
        assertFalse(enrolledStatus);
        verify(studentRepository).findById(studentId);
        verify(courseService).findById(courseId);
    }

    @Test
    void enrollCourseSuccessfully() {
        Integer studentId = 1;
        mockStudent.setCourseId(studentId);
        Integer courseId = 1;
        mockCourse.setId(courseId);
        when(studentRepository.findById(any())).thenReturn(Optional.of(mockStudent));
        when(courseService.findById(any())).thenReturn(Optional.of(mockCourse));
        boolean enrolledStatus = studentService.enrollCourse(studentId, courseId);
        assertTrue(enrolledStatus);
        verify(studentRepository).findById(studentId);
        verify(courseService).findById(courseId);
        verify(studentRepository).update(mockStudent);
        verify(courseService).update(mockCourse);
    }

    @Test
    void removeCourseSuccessfully() {
        Integer studentId = 1;
        mockStudent.setCourseId(studentId);
        Integer courseId = 1;
        List<Integer> ids = new ArrayList<>();
        ids.add(2);
        Course newCourse = Course.builder().id(courseId).maximumClassSize(5).enrolledStudents(ids).build();
        when(studentRepository.findById(any())).thenReturn(Optional.of(mockStudent));
        when(courseService.findById(any())).thenReturn(Optional.of(newCourse));
        boolean removeCourse = studentService.removeCourse(studentId, courseId);
        assertTrue(removeCourse);
        verify(studentRepository).findById(studentId);
        verify(courseService).findById(courseId);
        verify(studentRepository).update(mockStudent);
        verify(courseService).update(newCourse);
    }

    @Test
    void removeCourseShouldReturnFalseIfCourseInfoNotFound() {
        Integer studentId = 1;
        mockStudent.setCourseId(studentId);
        Integer courseId = 1;
        mockCourse.setId(courseId);
        when(studentRepository.findById(any())).thenReturn(Optional.of(mockStudent));
        when(courseService.findById(any())).thenReturn(Optional.empty());
        boolean removeCourse = studentService.removeCourse(studentId, courseId);
        assertFalse(removeCourse);
        verify(studentRepository).findById(studentId);
        verify(courseService).findById(courseId);
    }

    @Test
    void removeCourseShouldReturnFalseIfStudentInfoNotFound() {
        Integer studentId = 1;
        Integer courseId = 1;
        when(studentRepository.findById(any())).thenReturn(Optional.empty());
        boolean removeCourse = studentService.removeCourse(studentId, courseId);
        assertFalse(removeCourse);
        verify(studentRepository).findById(studentId);
    }

    @Test
    void findById() {
        Integer studentId = 1;
        when(studentRepository.findById(any())).thenReturn(Optional.of(mockStudent));
        Optional<Student> result = studentService.findById(studentId);
        assertTrue(result.isPresent());
        assertEquals(mockStudent, result.get());
        verify(studentRepository).findById(studentId);
    }
}