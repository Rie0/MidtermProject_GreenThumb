package com.GreenThumb.Mid.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {
//    Variables
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer projectId;
    @NotEmpty(message = "You must enter the project name")
    private String projectName;
    @Enumerated(EnumType.STRING)
    private ProjectType projectType;
    @OneToMany(mappedBy = "project")
    private List<Volunteer> volunteers;

//              Constructors
    public Project() {
    }
    public Project(String projectName, ProjectType projectType) {
        this.projectName = projectName;
        this.projectType = projectType;
    }

//              Getters and Setters

    public Integer getProjectId() {
        return projectId;
    }
    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
    public String getProjectName() {
        return this.projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public ProjectType getProjectType() {
        return this.projectType;
    }
    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }
    public List<Volunteer> getVolunteers() {
        return volunteers;
    }
    public void setVolunteers(List<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }

//             Methods
    public static Project createProject(String name, ProjectType type) {
        Project project = new Project();
        project.setProjectName(name);
        project.setProjectType(type);
        // Initialize the list of volunteers as empty initially
        project.setVolunteers(new ArrayList<>());
        return project;
    }
    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectType=" + projectType +
                '}';
    }
}