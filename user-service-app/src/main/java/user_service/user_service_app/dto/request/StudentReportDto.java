package user_service.user_service_app.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StudentReportDto {
    private double studentRegNo;
    private double mean;
    private double median;
    private Double mode;
}
