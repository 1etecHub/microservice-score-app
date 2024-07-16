package com.waysTech.scores_of_students.exceptions;

import org.springframework.http.HttpStatus;

public class CourseNotFoundException extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;

    public CourseNotFoundException(String message) {
        super(message);
    }

    public CourseNotFoundException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public CourseNotFoundException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
