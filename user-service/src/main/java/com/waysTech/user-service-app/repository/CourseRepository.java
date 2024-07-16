package com.waysTech.scores_of_students.repository;

import com.waysTech.scores_of_students.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByCourseCode(String courseCode);
    Optional<Course> findByCourseName(String courseName);
}
