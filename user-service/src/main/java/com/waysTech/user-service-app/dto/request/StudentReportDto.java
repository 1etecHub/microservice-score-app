package com.waysTech.scores_of_students.dto.request;

import com.waysTech.scores_of_students.entities.Student;
import com.waysTech.scores_of_students.entities.StudentCourse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StudentReportDto {
    private Student student;
    private List<StudentCourse> courses;
    private double mean;
    private double median;
    private Double mode;
}