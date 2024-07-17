package user_service.user_service_app.service.userService.userService;

import org.springframework.stereotype.Service;
import user_service.user_service_app.dto.request.RegistrationRequest;
import user_service.user_service_app.dto.response.GenericResponse;

@Service
public interface AppUserService {
    GenericResponse registerStudent(RegistrationRequest request);
}
