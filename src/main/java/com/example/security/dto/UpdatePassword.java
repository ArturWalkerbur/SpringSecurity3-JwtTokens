package com.example.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePassword {

    private String password;
    private String newPassword;
    private String reNewPassword;
    private String code;

    public UpdatePassword(String newPassword, String reNewPassword, String code) {
        this.newPassword = newPassword;
        this.reNewPassword = reNewPassword;
        this.code = code;
    }

}
