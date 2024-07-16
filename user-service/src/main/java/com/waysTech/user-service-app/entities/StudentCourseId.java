package com.waysTech.scores_of_students.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourseId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long studentId;
    private Long courseId;
}