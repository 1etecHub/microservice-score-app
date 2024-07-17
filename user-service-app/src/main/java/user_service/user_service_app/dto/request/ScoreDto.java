package user_service.user_service_app.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDto {

    private int englishScore;
    private int mathematicsScore;
    private int physicsScore;
    private int computerScore;
    private int chemistryScore;
    private int studentRegNo;


}
