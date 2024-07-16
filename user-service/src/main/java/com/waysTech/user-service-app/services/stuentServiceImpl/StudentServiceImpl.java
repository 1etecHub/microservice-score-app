package com.waysTech.scores_of_students.services.stuentServiceImpl;

import com.waysTech.scores_of_students.dto.request.RegistrationRequest;
import com.waysTech.scores_of_students.dto.response.GenericResponse;
import com.waysTech.scores_of_students.entities.*;
import com.waysTech.scores_of_students.enums.Role;
import com.waysTech.scores_of_students.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class StudentServiceImpl implements StudentService{

    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    private final StudentCourseRepository studentCourseRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public GenericResponse studentRegistration(RegistrationRequest registrationDTO) {
        Optional<Student> optionalUser = teacherRepository.findByEmail(registrationDTO.getEmail());
        if (optionalUser.isPresent()) {
            return new GenericResponse("User Already Exist");
        }
        Student newStudent = new Student();
        newStudent.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        newStudent.setFullName(registrationDTO.getFullName());
        newStudent.setPhoneNumber(registrationDTO.getPhoneNumber());
        newStudent.setIsVerified(true);
        newStudent.setEmail(registrationDTO.getEmail());
        newStudent.setRole(Role.STUDENT);
        teacherRepository.save(newStudent);
        GenericResponse genericResponse = new GenericResponse<>();
        genericResponse.setMessage("Registration Successful");
        return genericResponse;
    }

    @Override
    public StudentCourse registerStudentForCourse(String email, String courseCode) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findByCourseCode(courseCode)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        int registeredCoursesCount = studentCourseRepository.countByStudentId(student.getId());
        if (registeredCoursesCount >= 5) {
            throw new RuntimeException("Student has already registered for 5 courses");
        }
        StudentCourse studentCourse = new StudentCourse(student, course);
        student.getStudentCourses().add(studentCourse);
        course.getStudentCourses().add(studentCourse);
        studentCourseRepository.save(studentCourse);

        return studentCourse;
    }


}

