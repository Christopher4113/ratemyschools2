package com.ratemyschools.rate.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {
    private String email;
    private String username;
    private String password;
}
