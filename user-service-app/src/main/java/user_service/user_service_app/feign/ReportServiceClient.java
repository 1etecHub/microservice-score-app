package user_service.user_service_app.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "report-service", path = "/app/scores")
public interface ReportServiceClient {

    @GetMapping("/mean/{studentRegNo}")
    ResponseEntity<Integer> getMeanScore(@PathVariable("studentRegNo") long studentRegNo);

    @GetMapping("/median/{studentRegNo}")
    ResponseEntity<Integer> getMedianScore(@PathVariable("studentRegNo") long studentRegNo);

    @GetMapping("/mode/{studentRegNo}")
    ResponseEntity<Integer> getModeScore(@PathVariable("studentRegNo") long studentRegNo);

}
