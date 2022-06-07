package com.preservica.cli.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class DataStore {
    private List<Student> students;
    private List<Course> courses;
}
