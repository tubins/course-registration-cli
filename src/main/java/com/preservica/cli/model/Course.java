package com.preservica.cli.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Model to represent Course.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    private Integer id;
    private String name;
    private Integer maximumClassSize;
    private List<Integer> enrolledStudents = new ArrayList<>();
}
