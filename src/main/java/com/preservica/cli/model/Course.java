package com.preservica.cli.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    private Integer id;
    private String name;
    private Integer maximumClassSize;
    private List<Integer> enrolledStudents = new ArrayList<>();
    @JsonIgnore
    public boolean isCourseFullyEnrolled() {
        return (enrolledStudents.size()+ 1) > maximumClassSize;
    }
}
