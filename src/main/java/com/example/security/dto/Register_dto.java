package com.example.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Register_dto {

    private String email;
    private String password;
    private String fullName;
    private Date birthDate;
    private String gender;
    private Long lastDiagnosis;
    private String contact;
    private String rePassword;


}
