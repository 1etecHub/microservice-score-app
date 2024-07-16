package com.waysTech.scores_of_students.repository;


import com.waysTech.scores_of_students.entities.Student;
import com.waysTech.scores_of_students.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
    Page<Student> findByRole(Role role, Pageable pageable);
    Collection<Object> findAllByRole(Role role);
    List<Student> findAllByCreationDateBetween(LocalDateTime localDateTime, LocalDateTime localDateTime1);
    boolean existsByEmail(String mail);
}
