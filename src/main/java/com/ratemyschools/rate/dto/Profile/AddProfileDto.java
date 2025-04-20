package com.ratemyschools.rate.dto.Profile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProfileDto {
    private Long userId;
    private String location;
    private String major;
    private String academicLevel;
    private String campusSetting;
    private String finance;
    private String goals;
    private String living;
    private String personal;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getAcademicLevel() {
        return academicLevel;
    }

    public void setAcademicLevel(String academicLevel) {
        this.academicLevel = academicLevel;
    }

    public String getCampusSetting() {
        return campusSetting;
    }

    public void setCampusSetting(String campusSetting) {
        this.campusSetting = campusSetting;
    }

    public String getFinance() {
        return finance;
    }

    public void setFinance(String finance) {
        this.finance = finance;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getLiving() {
        return living;
    }

    public void setLiving(String living) {
        this.living = living;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }
}
