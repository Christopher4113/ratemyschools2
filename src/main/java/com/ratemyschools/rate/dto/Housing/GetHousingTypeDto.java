package com.ratemyschools.rate.dto.Housing;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetHousingTypeDto {
    private Long id;
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
