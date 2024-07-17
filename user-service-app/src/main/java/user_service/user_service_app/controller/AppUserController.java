package user_service.user_service_app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user_service.user_service_app.dto.request.RegistrationRequest;
import user_service.user_service_app.dto.request.ScoreDto;
import user_service.user_service_app.dto.request.StudentReportDto;
import user_service.user_service_app.dto.response.GenericResponse;
import user_service.user_service_app.service.userService.userService.AppUserService;
import user_service.user_service_app.utils.PasswordValidator;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/app")
public class AppUserController {


    private final AppUserService appUserService;
    private final PasswordValidator passwordValidator;
    private final ReportService reportService;
    private final ScoreService scoreService;

    @PostMapping(path = "/registerStudent")
    public ResponseEntity<?> registerStudent(@RequestBody @Valid RegistrationRequest request) {
        log.info("controller register: register user :: [{}] ::", request.getEmail());
        passwordValidator.isValid(request);
        GenericResponse response = appUserService.registerStudent(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("create")
    public ResponseEntity<String> enterScores (@RequestBody ScoreDto ScoreDto){
        scoreService.addScores(scoreDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Scores added successfully");
    }

    @PostMapping("get/{id}")
    public ResponseEntity<StudentReportDto> getReport(@PathVariable Integer registrationNumber){
        StudentReportDto studentReport = reportService.generateStudentReport(registrationNumber);
        return ResponseEntity.ok(studentReport);
    }


}
