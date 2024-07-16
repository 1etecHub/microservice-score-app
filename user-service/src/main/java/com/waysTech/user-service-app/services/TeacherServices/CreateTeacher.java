package com.waysTech.scores_of_students.services.TeacherServices;

import com.waysTech.scores_of_students.entities.Teacher;
import com.waysTech.scores_of_students.enums.Role;
import com.waysTech.scores_of_students.repository.TeacherRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Slf4j
@Service
public class CreateTeacher {
    private TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CreateTeacher(TeacherRepository teacherRepository, PasswordEncoder passwordEncoder) {
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
        runAtStart();
    }

    @PostConstruct
    public void runAtStart() {
        log.info("Creating Teacher");

        String teacherEmail = "bellowajiuo@gmail.com";

        if (!teacherRepository.existsByEmail(teacherEmail)) {
            Teacher teacher = new Teacher();
            teacher.setEmail(teacherEmail);
            teacher.setPassword(passwordEncoder.encode("OneAdmin246"));
            teacher.setRole(Role.TEACHER);
            teacher.setCreationDate(LocalDateTime.now());
            teacher.setLastLogin(LocalDateTime.now());
            teacher.setPhoneNumber("09039156872");
            teacher.setFullName("Wajiu Bello Olarewaju");
            teacher.setIsVerified(true);
            teacherRepository.save(teacher);
        }
    }
}
