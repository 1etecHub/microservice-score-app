package user_service.user_service_app.service.userService.userService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import user_service.user_service_app.dto.request.RegistrationRequest;
import user_service.user_service_app.dto.response.GenericResponse;
import user_service.user_service_app.entities.User;
import user_service.user_service_app.enums.Role;
import user_service.user_service_app.repositories.UserRepository;
import user_service.user_service_app.securities.JWTService;

import java.util.Optional;

@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService{

    private final JWTService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public GenericResponse registerStudent(RegistrationRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            return new GenericResponse("Student already exist", HttpStatus.BAD_REQUEST);
        }
        User newUser = new User();
        newUser.setFullName(request.getFullName());
        newUser.setPhoneNumber(request.getPhoneNumber());
        newUser.setIsVerified(true);
        newUser.setEmail(request.getEmail());
        newUser.setRole(Role.STUDENT);
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(newUser);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setMessage("You have successfully registered a student");
        genericResponse.setHttpStatus(HttpStatus.OK);
        return genericResponse;

    }


}
