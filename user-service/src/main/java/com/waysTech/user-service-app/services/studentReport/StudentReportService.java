package com.waysTech.scores_of_students.services.studentReport;

import com.waysTech.scores_of_students.dto.request.StudentReportDto;

public interface StudentReportService {
    StudentReportDto generateStudentReport(String email);
}
