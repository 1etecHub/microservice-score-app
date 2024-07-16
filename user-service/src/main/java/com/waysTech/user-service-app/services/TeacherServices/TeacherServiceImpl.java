package com.waysTech.scores_of_students.services.TeacherServices;


import com.waysTech.scores_of_students.dto.request.AddCourseRequest;
import com.waysTech.scores_of_students.dto.request.LoginRequest;
import com.waysTech.scores_of_students.dto.response.GenericResponse;
import com.waysTech.scores_of_students.dto.response.LoginResponse;
import com.waysTech.scores_of_students.dto.response.DetailResponse;
import com.waysTech.scores_of_students.entities.*;
import com.waysTech.scores_of_students.enums.Role;
import com.waysTech.scores_of_students.exceptions.CommonApplicationException;
import com.waysTech.scores_of_students.exceptions.CourseNotFoundException;
import com.waysTech.scores_of_students.exceptions.NotFoundException;
import com.waysTech.scores_of_students.exceptions.UserExistException;
import com.waysTech.scores_of_students.repository.*;
import com.waysTech.scores_of_students.securities.JWTService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService {
    private final JWTService jwtService;
    private final TeacherRepository teacherRepository;
    private final AuthenticationManager authenticationManager;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentCourseRepository studentCourseRepository;


    @Override
    public GenericResponse<LoginResponse> login(LoginRequest loginDTO) {
        log.info("Request to login at the service layer");

        Authentication authenticationUser;
        try {
            authenticationUser = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );
            log.info("Authenticated the User by the Authentication manager");
        } catch (DisabledException es) {
            return Stream.of(
                            new AbstractMap.SimpleEntry<>("message", "Disabled exception occurred"),
                            new AbstractMap.SimpleEntry<>("status", "failure"),
                            new AbstractMap.SimpleEntry<>("httpStatus", HttpStatus.BAD_REQUEST)
                    )
                    .collect(
                            Collectors.collectingAndThen(
                                    Collectors.toMap(AbstractMap.SimpleEntry::getKey, entry -> entry.getValue()),
                                    map -> new GenericResponse<>((Map<String, String>) map)
                            )
                    );

        } catch (BadCredentialsException e) {
            throw new UserExistException("Invalid email or password", HttpStatus.BAD_REQUEST);
        }
        // Tell securityContext that this user is in the context
        SecurityContextHolder.getContext().setAuthentication(authenticationUser);
        // Retrieve the user from the repository
        Student appStudent = teacherRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() ->
                new UserExistException("User not found", HttpStatus.BAD_REQUEST));
        // Update the lastLoginDate field
        appStudent.setLastLogin(LocalDateTime.now());
        log.info("last-login date updated");
        // Save the updated user entity
        Student student = teacherRepository.save(appStudent);
        log.info("user saved back to database");
        // Generate and send token
        String tokenGenerated = "Bearer " + jwtService.generateToken(authenticationUser, student.getRole());
        log.info("Jwt token generated for the user " + tokenGenerated);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(tokenGenerated);
        GenericResponse<LoginResponse> genericResponse = new GenericResponse<>("Successfully logged in",  loginResponse, HttpStatus.OK);

        genericResponse.setData(loginResponse);

        return genericResponse;
    }

    @Override
    public StudentCourse addOrUpdateStudentCourseScore(Long studentId, Long courseId, Double score) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found"));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException("Course not found"));
        StudentCourse studentCourse = student.getStudentCourses().stream()
                .filter(sc -> sc.getCourse().getId().equals(courseId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Student is not registered for this course"));

        studentCourse.setScore(score);
        return studentCourseRepository.save(studentCourse);
    }


    public List<Course> createCourses(List<AddCourseRequest> addCourseRequests, String email) {
        // Ensure user is an admin or delivery personnel
        checkUserIsAdminOrDeliveryPersonnel(email);
        List<Course> courses = new ArrayList<>();
        for (AddCourseRequest addCourseRequest : addCourseRequests) {
            // Check if the course already exists
            if (courseRepository.findByCourseName(addCourseRequest.courseName()).isPresent()) {
                throw new RuntimeException("Course with name " + addCourseRequest.courseName() + " already exists");
            }
            Course course = new Course();
            course.setCourseName(addCourseRequest.courseName());
            course.setCourseCode(addCourseRequest.courseCode());
            courses.add(course);
        }
        return courseRepository.saveAll(courses);
    }
    @Override
    public DetailResponse getUserDetails(String authorizationHeader) throws CommonApplicationException {
        String token = authorizationHeader.substring(7);
        Map<String, String> userDetails = jwtService.validateTokenAndReturnDetail(token);

        String userEmail = userDetails.get("email");
        String userName = userDetails.get("name");
        Role userRole = Role.valueOf(userDetails.get("role"));

        DetailResponse detailResponse = new DetailResponse();
        detailResponse.setFullName(userName);
        detailResponse.setEmail(userEmail);
        detailResponse.setRole(userRole);

        return detailResponse;
    }
    @Override
    public GenericResponse<String> logout(HttpServletRequest request, String authorizationHeader) throws CommonApplicationException {
        String token = authorizationHeader.substring(7);
        Map<String, String> userDetails = jwtService.validateTokenAndReturnDetail(token);
        log.info("Request to logout");
        try {
            SecurityContextHolder.getContext().setAuthentication(null);
            return new GenericResponse<>("Successfully logged out", HttpStatus.OK);
        } catch (Exception e) {
            return new GenericResponse<>("An error occurred while logging out", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void checkUserIsAdminOrDeliveryPersonnel(String email) {
        Optional<Student> user = studentRepository.findByEmail(email);

        if (user.isPresent()) {
            Role userRole = user.get().getRole();

            if (userRole == Role.TEACHER) {
                log.info("User with email '{}' has the role '{}'. Access granted.", email, userRole);
                return;
            }
        }
        log.info("Access denied. User with email '{}' is not an admin or delivery personnel.", email);
        throw new UserExistException("Only admins and delivery personnel can access this functionality.");
    }
}
