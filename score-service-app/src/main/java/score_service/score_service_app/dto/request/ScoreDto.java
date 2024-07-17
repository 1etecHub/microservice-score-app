package score_service.score_service_app.dto.request;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Data
@ToString
public class ScoreDto {
    private int englishScore;
    private int mathematicsScore;
    private int physicsScore;
    private int computerScore;
    private int chemistryScore;
    private int studentRegNo;
}
