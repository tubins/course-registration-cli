package com.preservica.cli.service;

import com.preservica.cli.model.Course;
import com.preservica.cli.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
 * Unit test of @{@link CourseServiceImpl} class.
 */
@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private Course mockCourse;

    @Test
    void save() {
        courseService.save(mockCourse);
        verify(courseRepository).save(mockCourse);
    }

    @Test
    void listAll() {
        when(courseRepository.findAll()).thenReturn(Collections.singletonList(mockCourse));
        List<Course> courses = courseService.listAll();
        assertNotNull(courses);
        assertEquals(mockCourse, courses.get(0));
        verify(courseRepository).findAll();
    }

    @Test
    void findById() {
        Integer courseId = 1;
        when(courseRepository.findById(any())).thenReturn(Optional.of(mockCourse));
        Optional<Course> result = courseService.findById(courseId);
        assertTrue(result.isPresent());
        assertEquals(mockCourse, result.get());
        verify(courseRepository).findById(courseId);
    }

    @Test
    void update() {
        courseService.update(mockCourse);
        verify(courseRepository).update(mockCourse);
    }

    @ParameterizedTest
    @CsvSource({"1,true", "2,false"})
    void isCourseFullyEnrolled(final Integer maxSize, final boolean expectedResult) {
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        Course newCourse = Course.builder().maximumClassSize(maxSize).enrolledStudents(ids).build();
        boolean courseFullyEnrolled = courseService.isCourseFullyEnrolled(newCourse);
        assertEquals(expectedResult, courseFullyEnrolled);
    }
}