package score_service.score_service_app.dto.reponse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
@ToString
public class GenericResponse<T> {


    private String message;
    private T data;

    @JsonIgnore
    private HttpStatus httpStatus;

    public GenericResponse(String message, T data, HttpStatus httpStatus) {
        this.message = message;
        this.data = data;
        this.httpStatus = httpStatus;

    }

    public GenericResponse(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public GenericResponse(Map<String, String> map) {
        this.message = map.get("message");
    }

    public GenericResponse(String message) {
        this.message = message;
    }
}

