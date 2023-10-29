package com.GreenThumb.Mid.model;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Volunteer {
//    Variables
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer volunteerId;
    @NotEmpty(message = "You must enter the volunteer's name")
    private String volunteerName;
    @Embedded
    @Valid //Enforces following the constraints inside the VolunteerInfo.
    private VolunteerInfo volunteerInfo;
    @ManyToOne
    private Project project;

//     Constructors
    public Volunteer() {
    }
    public Volunteer(String volunteerName, VolunteerInfo volunteerInfo) {
        this.volunteerName = volunteerName;
        this.volunteerInfo = volunteerInfo;
    }

    //     Get and Set
    public Integer getVolunteerId() {
        return volunteerId;
    }
    public void setVolunteerId(Integer volunteerId) {
        this.volunteerId = volunteerId;
    }
    public String getVolunteerName() {
        return volunteerName;
    }
    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }
    public VolunteerInfo getVolunteerInfo() {
        return volunteerInfo;
    }
    public void setVolunteerInfo(VolunteerInfo volunteerInfo) {
        this.volunteerInfo = volunteerInfo;
    }
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }

//    Methods
    public void addProject(Project project) {
    // Set the volunteer's project to the specified project
    this.project = project;
    // Add the volunteer to the project's list of volunteers
    project.getVolunteers().add(this);
}
    @Override
    public String toString() {
        return "Volunteer{" +
                "volunteerId=" + volunteerId +
                ", volunteerName='" + volunteerName + '\'' +
                ", volunteerInfo=" + volunteerInfo +
                '}';
    }
}
