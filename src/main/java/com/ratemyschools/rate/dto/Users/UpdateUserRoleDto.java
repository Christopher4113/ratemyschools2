package com.ratemyschools.rate.dto.Users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRoleDto {
    private Boolean isAdmin;

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }
}
