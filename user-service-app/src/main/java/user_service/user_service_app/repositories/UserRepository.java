package user_service.user_service_app.repositories;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import user_service.user_service_app.entities.User;
import user_service.user_service_app.enums.Role;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional <User> findByRegistrationNumber(double registrationNumber);
    Collection<Object> findAllByRole(Role role);
    List<User> findAllByCreationDateBetween(LocalDateTime localDateTime, LocalDateTime localDateTime1);
    boolean existsByEmail(String mail);
}
