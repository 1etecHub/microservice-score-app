package com.waysTech.scores_of_students.repository;

import com.waysTech.scores_of_students.entities.StudentCourse;
import com.waysTech.scores_of_students.entities.StudentCourseId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, StudentCourseId> {
    int countByStudentId(Long studentId);
}