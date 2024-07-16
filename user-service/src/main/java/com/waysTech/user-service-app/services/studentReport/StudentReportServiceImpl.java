package com.waysTech.scores_of_students.services.studentReport;

import com.waysTech.scores_of_students.dto.request.StudentReportDto;
import com.waysTech.scores_of_students.entities.StudentCourse;
import com.waysTech.scores_of_students.entities.Student;
import com.waysTech.scores_of_students.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentReportServiceImpl implements StudentReportService{
    private final StudentRepository studentRepository;

    public StudentReportDto generateStudentReport(String email) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        List<StudentCourse> courses = student.getStudentCourses();

        List<Double> scores = courses.stream()
                .map(StudentCourse::getScore)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        double mean = calculateMean(scores);
        double median = calculateMedian(scores);
        Double mode = calculateMode(scores);

        return new StudentReportDto(student, courses, mean, median, mode);
    }

    private double calculateMean(List<Double> scores) {
        return scores.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    private double calculateMedian(List<Double> scores) {
        Collections.sort(scores);
        int size = scores.size();
        if (size % 2 == 0) {
            return (scores.get(size / 2 - 1) + scores.get(size / 2)) / 2.0;
        } else {
            return scores.get(size / 2);
        }
    }

    private Double calculateMode(List<Double> scores) {
        Map<Double, Long> frequencyMap = scores.stream()
                .collect(Collectors.groupingBy(score -> score, Collectors.counting()));

        return frequencyMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}

