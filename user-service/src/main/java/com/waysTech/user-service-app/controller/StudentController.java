package com.waysTech.scores_of_students.controller;


import com.waysTech.scores_of_students.dto.request.LoginRequest;
import com.waysTech.scores_of_students.dto.request.RegistrationRequest;
import com.waysTech.scores_of_students.dto.response.GenericResponse;
import com.waysTech.scores_of_students.dto.response.LoginResponse;
import com.waysTech.scores_of_students.dto.response.DetailResponse;
import com.waysTech.scores_of_students.entities.StudentCourse;
import com.waysTech.scores_of_students.exceptions.CommonApplicationException;
import com.waysTech.scores_of_students.services.TeacherServices.TeacherService;
import com.waysTech.scores_of_students.services.stuentServiceImpl.StudentService;
import com.waysTech.scores_of_students.validation.PasswordValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class StudentController {
    private final TeacherService teacherService;
    private final PasswordValidator passwordValidator;
    private final StudentService studentService;

    @PostMapping(path = "/login")
    public ResponseEntity<GenericResponse<LoginResponse>> loginUser(@RequestBody @Valid LoginRequest request) {
        log.info("request to login user");
        GenericResponse<LoginResponse> response = teacherService.login(request);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationRequest request) {
        log.info("controller register: register user :: [{}] ::", request.getEmail());
        passwordValidator.isValid(request);
        GenericResponse response = studentService.studentRegistration(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/userdetails")
    public ResponseEntity<GenericResponse<DetailResponse>> getUserDetails(
            @RequestHeader("Authorization") String authorizationHeader
    ) throws CommonApplicationException, CommonApplicationException {
        log.info("Received request with Authorization Header: {}", authorizationHeader);
        DetailResponse detailResponse = teacherService.getUserDetails(authorizationHeader);
        GenericResponse<DetailResponse> genericResponse = new GenericResponse<>("User detail retrieved successfully", detailResponse, HttpStatus.OK);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }
    @PostMapping("/logout")
    public ResponseEntity<GenericResponse<String>> logout(HttpServletRequest request, @RequestHeader("Authorization") String authorizationHeader) throws CommonApplicationException {
        log.info("Received request with Authorization Header: {}", authorizationHeader);
        GenericResponse<String> response = teacherService.logout(request, authorizationHeader);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }



}
