package com.GreenThumb.Mid.model;


import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Embeddable
public class VolunteerInfo {
//    Variables
    @NotEmpty(message = "You must enter the volunteer's phone number.")
    @Pattern(regexp = "^[0-9]{10}$", message ="Enter a valid Phone number.") //must be 10 digits long. only numbers accepted
    private String phoneNumber;
    @NotEmpty(message = "You must enter the volunteer's email.")
    @Email(message = "You must enter a valid volunteer email.")
    private String volunteerEmail;

//    Constructors
    public VolunteerInfo() {
    }
    public VolunteerInfo(String phoneNumber, String volunteerEmail) {
        this.phoneNumber = phoneNumber;
        this.volunteerEmail = volunteerEmail;
    }

//    Getters and Setters
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getVolunteerEmail() {
        return volunteerEmail;
    }
    public void setVolunteerEmail(String volunteerEmail) {
        this.volunteerEmail = volunteerEmail;
    }

//    Methods
    @Override
    public String toString() {
        return "VolunteerInfo{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", volunteerEmail='" + volunteerEmail + '\'' +
                '}';
    }
}
