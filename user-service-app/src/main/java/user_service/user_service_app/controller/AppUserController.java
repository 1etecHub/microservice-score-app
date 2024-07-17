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

import java.util.Map;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/app/Users")
public class AppUserController {


    private final AppUserService appUserService;
    private final PasswordValidator passwordValidator;

    @PostMapping(path = "/registerStudent")
    public ResponseEntity<?> registerStudent(@RequestBody @Valid RegistrationRequest request) {
        log.info("controller register: register user :: [{}] ::", request.getEmail());
        passwordValidator.isValid(request);
        GenericResponse response = appUserService.registerStudent(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("create")
    public ResponseEntity<String> enterScores (@RequestBody ScoreDto scoreDto){
        appUserService.addScores(scoreDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Scores added successfully");
    }

    @PostMapping("get/{registrationNumber}")
    public ResponseEntity<GenericResponse<Map<String, Integer>>> getStudentScore(@PathVariable("registrationNumber") long registrationNumber) {
        GenericResponse<Map<String, Integer>> studentReport = appUserService.getStudentScore(registrationNumber);
        if (studentReport == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(studentReport);
        }
    }


    @GetMapping("/mean/{studentRegNo}")
    public ResponseEntity<GenericResponse<Integer>> getMeanScore(@PathVariable long studentRegNo) {
        return appUserService.getMeanScore(studentRegNo);
    }

    @GetMapping("/median/{studentRegNo}")
    public ResponseEntity<Integer> getMedianScore(@PathVariable long studentRegNo) {
        return appUserService.getMedianScore(studentRegNo);
    }

    @GetMapping("/mode/{studentRegNo}")
    public ResponseEntity<Integer> getModeScore(@PathVariable long studentRegNo) {
        return appUserService.getModeScore(studentRegNo);
    }


}
