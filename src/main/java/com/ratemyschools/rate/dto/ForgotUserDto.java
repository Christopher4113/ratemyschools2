package com.ratemyschools.rate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotUserDto {
    private String email;
    private String forgotCode;
    private String newPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getForgotCode() {
        return forgotCode;
    }

    public void setForgotCode(String forgotCode) {
        this.forgotCode = forgotCode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
