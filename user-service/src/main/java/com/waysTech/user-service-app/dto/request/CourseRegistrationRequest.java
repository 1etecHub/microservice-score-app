package com.waysTech.scores_of_students.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CourseRegistrationRequest(@NotBlank(message = "Course name is required")String email,
                                        @NotBlank(message = "Course name is required")String courseCode) {
}
