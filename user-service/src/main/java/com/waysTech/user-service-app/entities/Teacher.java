package com.waysTech.scores_of_students.entities;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Teacher extends Student {
    private static  final long serialVersionUID=1l;
}
