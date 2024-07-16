package com.waysTech.scores_of_students.controller;

import com.waysTech.scores_of_students.dto.request.AddCourseRequest;
import com.waysTech.scores_of_students.entities.Course;
import com.waysTech.scores_of_students.entities.StudentCourse;
import com.waysTech.scores_of_students.exceptions.CommonApplicationException;
import com.waysTech.scores_of_students.securities.JWTService;
import com.waysTech.scores_of_students.services.TeacherServices.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/v1/teacher"})
@RequiredArgsConstructor
@Slf4j
public class TeacherController {

    private final TeacherService teacherService;
    private final JWTService jwtService;


    @PostMapping("/{studentId}/course/{courseId}/score")
    public ResponseEntity<StudentCourse> addOrUpdateScore(@PathVariable Long studentId, @PathVariable Long courseId, @RequestParam Double score) {
        StudentCourse studentCourse = teacherService.addOrUpdateStudentCourseScore(studentId, courseId, score);
        return ResponseEntity.ok(studentCourse);
    }


    @PostMapping("/add-course")
    public ResponseEntity<List<Course>> createCourses(@RequestBody List<AddCourseRequest> addCourseRequests, @RequestHeader("Authorization") String authorizationHeader) throws CommonApplicationException {
        var userDetails = jwtService.validateTokenAndReturnDetail(authorizationHeader.substring(7));
        log.info("User details from token: {}", userDetails); // Log user details
        String userEmail = userDetails.get("email");
        List<Course> courses = teacherService.createCourses(addCourseRequests, userEmail);
        return ResponseEntity.ok(courses);
    }

}
