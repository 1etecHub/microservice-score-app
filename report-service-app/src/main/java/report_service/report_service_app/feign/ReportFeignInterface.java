package report_service.report_service_app.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import report_service.report_service_app.dto.Subject;

@FeignClient(name = "score-service", path = "/app/scores")
public interface ReportFeignInterface {

    @GetMapping("/getSubjectScore/{studentRegNo}")
    Subject getSubjectScoreByStudentRegNo(@PathVariable long studentRegNo);


}
