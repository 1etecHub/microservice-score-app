package com.waysTech.scores_of_students.services.TeacherServices;


import com.waysTech.scores_of_students.dto.request.AddCourseRequest;
import com.waysTech.scores_of_students.dto.request.LoginRequest;
import com.waysTech.scores_of_students.dto.request.RegistrationRequest;
import com.waysTech.scores_of_students.dto.response.GenericResponse;
import com.waysTech.scores_of_students.dto.response.LoginResponse;
import com.waysTech.scores_of_students.dto.response.DetailResponse;
import com.waysTech.scores_of_students.entities.Course;
import com.waysTech.scores_of_students.entities.StudentCourse;
import com.waysTech.scores_of_students.exceptions.CommonApplicationException;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface TeacherService {
    GenericResponse<LoginResponse> login(LoginRequest loginDTO);
    StudentCourse addOrUpdateStudentCourseScore(Long studentId, Long courseId, Double score);
    List<Course> createCourses(List<AddCourseRequest> addCourseRequests, String email);
    DetailResponse getUserDetails(String authorizationHeader) throws CommonApplicationException;

    GenericResponse<String> logout(HttpServletRequest request, String authorizationHeader) throws CommonApplicationException;

}
