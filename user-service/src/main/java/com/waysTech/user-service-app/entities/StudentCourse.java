package com.waysTech.scores_of_students.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Table(name = "student_courses")
@Getter
@Setter
@NoArgsConstructor
public class StudentCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private StudentCourseId id = new StudentCourseId();

    @ManyToOne
    @MapsId("studentId")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    private Course course;

    private Double score;

    public StudentCourse(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.id = new StudentCourseId(student.getId(), course.getId());
    }
}
