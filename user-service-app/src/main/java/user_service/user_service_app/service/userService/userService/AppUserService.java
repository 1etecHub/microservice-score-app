package user_service.user_service_app.service.userService.userService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import user_service.user_service_app.dto.request.RegistrationRequest;
import user_service.user_service_app.dto.request.ScoreDto;
import user_service.user_service_app.dto.response.GenericResponse;

import java.util.Map;

@Service
public interface AppUserService {
    GenericResponse registerStudent(RegistrationRequest request);

    ResponseEntity<String> addScores(ScoreDto scoreDTO);

    GenericResponse<Map<String, Integer>> getStudentScore(long studentRegNo);

    ResponseEntity<Integer> getMeanScore(long studentRegNo);

    ResponseEntity<Integer> getMedianScore(long studentRegNo);

    ResponseEntity<Integer> getModeScore(long studentRegNo);
}
