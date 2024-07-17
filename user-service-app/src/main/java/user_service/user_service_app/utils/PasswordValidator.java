package user_service.user_service_app.utils;



import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import user_service.user_service_app.dto.request.RegistrationRequest;
import user_service.user_service_app.exceptions.UserExistException;

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
