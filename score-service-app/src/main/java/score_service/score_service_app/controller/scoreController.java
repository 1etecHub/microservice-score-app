package score_service.score_service_app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import score_service.score_service_app.dto.request.ScoreDto;
import score_service.score_service_app.entities.Subject;
import score_service.score_service_app.service.ScoreService;

@RequiredArgsConstructor
@RestController
public class scoreController {


    private final ScoreService scoreService;

    @PostMapping("/scores")
    public ResponseEntity<String> addScores(@RequestBody ScoreDto scoreDTO) {
        scoreService.addScores(scoreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Scores added successfully");
    }


    @GetMapping("/{studentRegNo}")
    public ResponseEntity<Subject> getSubjectByStudentRegNo(@PathVariable double studentRegNo) {
        Subject subject = scoreService.getSubjectByStudentRegNo(studentRegNo);
        if (subject == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subject);
    }


    /*@GetMapping("/students/{registrationNumber}")
    public ResponseEntity<StudentReportDTO> getStudentReport(@PathVariable int registrationNumber) {
        StudentReportDTO studentReport = reportService.generateStudentReport(registrationNumber);
        return ResponseEntity.ok(studentReport);
    }*/




}
