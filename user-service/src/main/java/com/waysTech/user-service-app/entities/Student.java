package com.waysTech.scores_of_students.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.waysTech.scores_of_students.enums.Gender;
import com.waysTech.scores_of_students.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String email;

    // @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    private Gender gender;

    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime lastLogin;

    @Column
    @CreationTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime creationDate;

    @Column
    @UpdateTimestamp
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column
    private String uuid;

    private Boolean isVerified = true;

    @PrePersist
    public void generateStudentId() {
        String prefix = ("SDT-ID-" + generateNumericOTP());
        studentId = prefix;
    }

    private String generateNumericOTP() {
        Random random = new Random();
        int otpLength = 6;
        StringBuilder otpBuilder = new StringBuilder();
        for (int i = 0; i < otpLength; i++) {
            int digit = random.nextInt(10);
            otpBuilder.append(digit);
        }
        return otpBuilder.toString();
    }

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<StudentCourse> studentCourses = new ArrayList<>();

    public void addCourse(StudentCourse course) {
        if (studentCourses.size() < 5) {
            studentCourses.add(course);
            course.setStudent(this);
        } else {
            throw new RuntimeException("Maximum limit of courses (5) reached for this student");
        }
    }

    public void removeCourse(StudentCourse course) {
        studentCourses.remove(course);
        course.setStudent(null);
    }
}
