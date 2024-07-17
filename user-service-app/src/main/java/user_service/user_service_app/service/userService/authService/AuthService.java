package user_service.user_service_app.service.userService.authService;

import jakarta.servlet.http.HttpServletRequest;
import user_service.user_service_app.dto.request.LoginRequest;
import user_service.user_service_app.dto.response.DetailResponse;
import user_service.user_service_app.dto.response.GenericResponse;
import user_service.user_service_app.dto.response.LoginResponse;
import user_service.user_service_app.exceptions.CommonApplicationException;

public interface AuthService {
    GenericResponse<LoginResponse> login(LoginRequest loginDTO);

    DetailResponse getUserDetails(String authorizationHeader) throws CommonApplicationException;

    GenericResponse<String> logout(HttpServletRequest request, String authorizationHeader) throws CommonApplicationException;
}
