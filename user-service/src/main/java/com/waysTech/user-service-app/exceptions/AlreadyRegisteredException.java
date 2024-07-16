package com.waysTech.scores_of_students.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class AlreadyRegisteredException extends RuntimeException {

    private String message;
    private HttpStatus httpStatus;
    public AlreadyRegisteredException(String message) {
        super(message);

    }
    public AlreadyRegisteredException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
