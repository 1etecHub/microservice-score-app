package user_service.user_service_app.dto.response;
import lombok.Data;
import user_service.user_service_app.enums.Role;

@Data
public class DetailResponse {
    private String fullName;
    private String email;
    private Role role;
}
