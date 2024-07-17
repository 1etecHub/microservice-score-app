package user_service.user_service_app.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user_service.user_service_app.dto.request.ScoreDto;
import user_service.user_service_app.dto.response.GenericResponse;

import java.util.List;
import java.util.Map;

@FeignClient(name = "score-service", path = "/app/scores")
public interface UserFeignInterface {
    @PostMapping("/addScores")
    ResponseEntity<String> addScores(@RequestBody ScoreDto scoreDTO);

    @GetMapping("/getSubjectScore/{studentRegNo}")
    ResponseEntity<GenericResponse<Map<String, Integer>>> getSubjectScoreByStudentRegNo(@PathVariable long studentRegNo);



}
