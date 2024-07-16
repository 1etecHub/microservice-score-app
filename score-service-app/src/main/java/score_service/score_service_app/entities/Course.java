package score_service.score_service_app.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int englishScore;

    private int mathematicsScore;

    private int physicsScore;

    private int computerScore;

    private int chemistryScore;

    private int mean;

    private int median;

    private int mode;

    private int studentRegNo;






}
