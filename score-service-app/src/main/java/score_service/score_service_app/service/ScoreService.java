package score_service.score_service_app.service;

import org.springframework.stereotype.Service;
import score_service.score_service_app.dto.reponse.GenericResponse;
import score_service.score_service_app.dto.request.ScoreDto;
import score_service.score_service_app.entities.Subject;

import java.util.Map;

@Service
public interface ScoreService {



    void addScores(ScoreDto scoreDTO);


    GenericResponse<Map<String, Integer>> getSubjectScoreByStudentRegNo(long studentRegNo);
}
