package score_service.score_service_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import score_service.score_service_app.entities.Course;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByStudentRegNo(Long studentRegNo);

}
