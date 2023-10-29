package com.GreenThumb.Mid.service.interfaces;

import com.GreenThumb.Mid.model.Project;

public interface IProjectService {
    public Project getProjectById(Integer projectID);
    void updateProjectName(String projectName, Integer projectID);
    void deleteProject(Integer projectID);
}
