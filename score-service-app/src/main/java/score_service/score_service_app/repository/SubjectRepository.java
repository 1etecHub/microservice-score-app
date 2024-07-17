package score_service.score_service_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import score_service.score_service_app.entities.Subject;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Optional<Subject> findByStudentRegNo(Long studentRegNo);

}
