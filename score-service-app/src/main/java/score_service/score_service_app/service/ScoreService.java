package score_service.score_service_app.service;

import score_service.score_service_app.dto.request.ScoreDto;
import score_service.score_service_app.entities.Subject;

public interface ScoreService {



    void addScores(ScoreDto scoreDTO);


    Subject getSubjectByStudentRegNo(double studentRegNo);

}
