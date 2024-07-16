package com.waysTech.scores_of_students.dto.response;
import com.waysTech.scores_of_students.enums.Role;
import lombok.Data;
@Data
public class DetailResponse {
    private String fullName;
    private String email;
    private Role role;
}
