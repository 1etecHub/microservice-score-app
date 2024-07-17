package user_service.user_service_app.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user_service.user_service_app.dto.request.LoginRequest;
import user_service.user_service_app.dto.request.RegistrationRequest;
import user_service.user_service_app.dto.response.DetailResponse;
import user_service.user_service_app.dto.response.GenericResponse;
import user_service.user_service_app.dto.response.LoginResponse;
import user_service.user_service_app.exceptions.CommonApplicationException;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthController {
    private final TeacherService teacherService;
    private final PasswordValidator passwordValidator;
    private final StudentService studentService;

    @PostMapping(path = "/login")
    public ResponseEntity<GenericResponse<LoginResponse>> loginUser(@RequestBody @Valid LoginRequest request) {
        log.info("request to login user");
        GenericResponse<LoginResponse> response = userService.login(request);
        return ResponseEntity.status(response.getHttpStatus()).body(response);
    }


    @GetMapping("/userdetails")
    public ResponseEntity<GenericResponse<DetailResponse>> getUserDetails(
            @RequestHeader("Authorization") String authorizationHeader
    ) throws CommonApplicationException, CommonApplicationException {
        log.info("Received request with Authorization Header: {}", authorizationHeader);
        DetailResponse detailResponse = userService.getUserDetails(authorizationHeader);
        GenericResponse<DetailResponse> genericResponse = new GenericResponse<>("User detail retrieved successfully", detailResponse, HttpStatus.OK);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }*/
    @PostMapping("/logout")
    public ResponseEntity<GenericResponse<String>> logout(HttpServletRequest request, @RequestHeader("Authorization") String authorizationHeader) throws CommonApplicationException {
        log.info("Received request with Authorization Header: {}", authorizationHeader);
        GenericResponse<String> response = userService.logout(request, authorizationHeader);
        return new ResponseEntity<>(response, response.getHttpStatus());
    }





}
