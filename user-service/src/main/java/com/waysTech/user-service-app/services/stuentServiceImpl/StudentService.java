package com.waysTech.scores_of_students.services.stuentServiceImpl;

import com.waysTech.scores_of_students.dto.request.RegistrationRequest;
import com.waysTech.scores_of_students.dto.response.GenericResponse;
import com.waysTech.scores_of_students.entities.StudentCourse;

public interface StudentService {
    GenericResponse studentRegistration(RegistrationRequest registrationDTO);
    StudentCourse registerStudentForCourse(String email, String courseCode);
}
