package user_service.user_service_app.service.userService.userService;


import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import user_service.user_service_app.entities.User;
import user_service.user_service_app.enums.Role;
import user_service.user_service_app.repositories.UserRepository;

import java.time.LocalDateTime;


@Slf4j
@Service
public class CreateTeacher {
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CreateTeacher(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        runAtStart();
    }

    @PostConstruct
    public void runAtStart() {
        log.info("Creating Teacher");

        String teacherEmail = "bellowajiuo@gmail.com";

        if (!userRepository.existsByEmail(teacherEmail)) {
            User teacher = new User();
            teacher.setEmail(teacherEmail);
            teacher.setPassword(passwordEncoder.encode("OneAdmin246"));
            teacher.setRole(Role.TEACHER);
            teacher.setCreationDate(LocalDateTime.now());
            teacher.setLastLogin(LocalDateTime.now());
            teacher.setPhoneNumber("09039156872");
            teacher.setFullName("Wajiu Bello Olarewaju");
            teacher.setIsVerified(true);
            userRepository.save(teacher);
        }
    }
}
