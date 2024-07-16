package com.waysTech.scores_of_students.validation;


import com.waysTech.scores_of_students.dto.request.RegistrationRequest;
import com.waysTech.scores_of_students.exceptions.UserExistException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PasswordValidator {

    public Boolean isValid(RegistrationRequest userRegistrationRequestDto) {
        String password = userRegistrationRequestDto.getPassword();
        String confirmPassword = userRegistrationRequestDto.getConfirmPassword();

        if (Objects.equals(password, confirmPassword)) {
            return true;
        } else {
            throw new UserExistException("password do not match", HttpStatus.BAD_REQUEST);
        }
    }

}
