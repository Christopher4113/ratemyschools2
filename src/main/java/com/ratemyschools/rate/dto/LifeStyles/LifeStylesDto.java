package com.ratemyschools.rate.dto.LifeStyles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LifeStylesDto {
    private Long id;
    private String category;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
