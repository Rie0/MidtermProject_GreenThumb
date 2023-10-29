package com.GreenThumb.Mid.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class VolunteerEmailDTO {
    @NotEmpty(message = "You must enter the volunteer's email.")
    @Email(message = "You must enter a valid volunteer's email.")
    private String volunteerEmail;
    public String getVolunteerEmail() {
        return volunteerEmail;
    }
}
