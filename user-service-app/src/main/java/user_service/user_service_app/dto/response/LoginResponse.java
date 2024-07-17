package user_service.user_service_app.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse extends GenericResponse {
    private String token;




}
