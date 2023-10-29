package com.GreenThumb.Mid.controller.interfaces;

import com.GreenThumb.Mid.controller.dto.ProjectNameDTO;
import com.GreenThumb.Mid.model.Project;

import java.util.List;

public interface IProjectController {
    public List<Project> getAllProjects();
    public Project getProjectById(Integer projectID);
    public void saveProject(Project project);
    public void updateProjectName(ProjectNameDTO projectNameDTO,Integer projectID);
    public void deleteProject(Integer projectID);
}
