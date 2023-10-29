package com.GreenThumb.Mid.controller.dto;

import jakarta.validation.constraints.NotEmpty;

public class ProjectNameDTO {
    @NotEmpty(message = "You must enter the project name")
    private String projectName;

    public String getProjectName() {
        return projectName;
    }
}
