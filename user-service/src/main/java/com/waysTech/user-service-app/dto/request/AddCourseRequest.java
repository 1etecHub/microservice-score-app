package com.waysTech.scores_of_students.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AddCourseRequest (@NotBlank(message = "Course name is required") String courseName,
                                @NotBlank(message = "Course code is required") String courseCode){
}
