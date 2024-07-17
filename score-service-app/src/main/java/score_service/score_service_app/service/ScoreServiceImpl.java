package score_service.score_service_app.service;


import lombok.RequiredArgsConstructor;
import score_service.score_service_app.dto.request.ScoreDto;
import score_service.score_service_app.entities.Subject;
import score_service.score_service_app.repository.SubjectRepository;

import java.util.Arrays;

@RequiredArgsConstructor
public class ScoreServiceImpl implements ScoreService {


    private final SubjectRepository subjectRepository;

    @Override
    public void addScores(ScoreDto scoreDTO) {
        Subject subject = new Subject();
        subject.setEnglishScore(scoreDTO.getEnglishScore());
        subject.setMathematicsScore(scoreDTO.getMathematicsScore());
        subject.setPhysicsScore(scoreDTO.getPhysicsScore());
        subject.setComputerScore(scoreDTO.getComputerScore());
        subject.setChemistryScore(scoreDTO.getChemistryScore());
        subject.setStudentRegNo(scoreDTO.getStudentRegNo());


        subjectRepository.save(subject);
    }

    @Override
    public Subject getSubjectByStudentRegNo(double studentRegNo) {
        return null;
    }


}
