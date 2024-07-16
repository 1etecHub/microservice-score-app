package com.waysTech.scores_of_students.controller;

import com.waysTech.scores_of_students.dto.request.CourseRegistrationRequest;
import com.waysTech.scores_of_students.dto.request.StudentReportDto;
import com.waysTech.scores_of_students.entities.StudentCourse;
import com.waysTech.scores_of_students.securities.JWTService;
import com.waysTech.scores_of_students.services.studentReport.StudentReportService;
import com.waysTech.scores_of_students.services.stuentServiceImpl.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student/")
public class StudentReportController {
    private final StudentReportService studentReportService;
    private final StudentService studentService;

    @Autowired
    public StudentReportController(StudentReportService studentReportService, StudentService studentService) {
        this.studentReportService = studentReportService;
        this.studentService = studentService;

    }

    @GetMapping("report/{email}")
    public ResponseEntity<StudentReportDto> generateReport(@PathVariable String email) {
        StudentReportDto report = studentReportService.generateStudentReport(email);
        return ResponseEntity.ok(report);
    }

    @PostMapping("register-for-course")
    public ResponseEntity<StudentCourse> registerCourse(@RequestBody CourseRegistrationRequest request) {
        StudentCourse studentCourse = studentService.registerStudentForCourse(request.email(), request.courseCode());
        return ResponseEntity.ok(studentCourse);
    }
}
