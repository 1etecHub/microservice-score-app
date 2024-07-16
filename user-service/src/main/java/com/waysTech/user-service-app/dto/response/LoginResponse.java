package com.waysTech.scores_of_students.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse extends GenericResponse {
    private String token;

    public LoginResponse(Map map) {
        super(map);
    }

//    public LoginResponse(String message, Object data) {
//        super(message, data,HttpStatus.OK);
//    }


}
