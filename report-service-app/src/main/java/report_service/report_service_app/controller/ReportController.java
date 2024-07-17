package report_service.report_service_app.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import report_service.report_service_app.service.ReportService;

@RestController
@RequiredArgsConstructor
public class ReportController {


    private final ReportService reportService;

    @GetMapping("/mean/{studentRegNo}")
    public ResponseEntity<Integer> getMeanScore(@PathVariable long studentRegNo) {
        int meanScore = reportService.calculateMean(studentRegNo);
        return ResponseEntity.ok(meanScore);
    }

    @GetMapping("/median/{studentRegNo}")
    public ResponseEntity<Integer> getMedianScore(@PathVariable int studentRegNo) {
        int medianScore = reportService.calculateMedian(studentRegNo);
        return ResponseEntity.ok(medianScore);
    }

    @GetMapping("/mode/{studentRegNo}")
    public ResponseEntity<Integer> getModeScore(@PathVariable int studentRegNo) {
        int modeScore = reportService.calculateMode(studentRegNo);
        return ResponseEntity.ok(modeScore);
    }
}
