package com.example.security.dto;


import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User_dto {

    private String email;
    private String password;
    private String fullName;
    private Date birthDate;
    private String contact;

    public User_dto(String fullName, Date birthDate, String contact) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.contact = contact;
    }

    public User_dto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
