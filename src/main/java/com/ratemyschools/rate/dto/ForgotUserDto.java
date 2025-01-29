package com.ratemyschools.rate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotUserDto {
    private String email;
    private String forgotCode;

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
}
