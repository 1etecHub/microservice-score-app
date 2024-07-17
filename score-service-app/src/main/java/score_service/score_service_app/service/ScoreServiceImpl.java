package score_service.score_service_app.service;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import score_service.score_service_app.dto.reponse.GenericResponse;
import score_service.score_service_app.dto.request.ScoreDto;
import score_service.score_service_app.entities.Subject;
import score_service.score_service_app.repository.SubjectRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    public GenericResponse<Map<String, Integer>> getSubjectScoreByStudentRegNo(long studentRegNo) {
        Optional<Subject> subjectOptional = subjectRepository.findByStudentRegNo(studentRegNo);
        if (subjectOptional == null) {
            // Handle case where subject is not found for studentRegNo
            return new GenericResponse<>("scores not found for student with RegNo", HttpStatus.BAD_REQUEST);
        }
        Subject subject = subjectOptional.get();

        Map<String, Integer> scoresMap = new HashMap<>();
        scoresMap.put("English", subject.getEnglishScore());
        scoresMap.put("Mathematics", subject.getMathematicsScore());
        scoresMap.put("Physics", subject.getPhysicsScore());
        scoresMap.put("Computer", subject.getComputerScore());
        scoresMap.put("Chemistry", subject.getChemistryScore());

        return new GenericResponse<>("scores retrieved successfully!!", scoresMap, HttpStatus.OK);
    }


}
