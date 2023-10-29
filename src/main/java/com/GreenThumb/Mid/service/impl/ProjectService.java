package com.GreenThumb.Mid.service.impl;


import com.GreenThumb.Mid.model.Project;
import com.GreenThumb.Mid.repository.ProjectRepository;
import com.GreenThumb.Mid.service.interfaces.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProjectService implements IProjectService {

    @Autowired
    ProjectRepository projectRepository;
//  ***************************************************  GET  ****************************************************
    @Override // return project by ID
    public Project getProjectById(@PathVariable Integer projectID){
        Optional<Project> projectOptional = projectRepository.findById(projectID);
        if(projectOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Project with ID: "+ projectID +" not found"); //Check if the project exists, send a message if not.
        return projectOptional.get();
    }
//  ****************************************************  PATCH  ****************************************************
    @Override // update project name by ID
    public void updateProjectName(String projectName, Integer projectID) {
        Optional<Project> projectOptional = projectRepository.findById(projectID);
        if(projectOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Project with ID: "+ projectID +" not found"); //Check if the project exists, send a message if not.
        Project project = projectOptional.get();
        project.setProjectName(projectName); //set the new project name
        projectRepository.save(project); //save the updated project to the repository
    }
//  **************************************************  DELETE  ********************************************************
    @Override // remove project by ID
    public void deleteProject(Integer projectID) {
        Optional<Project> projectOptional = projectRepository.findById(projectID);
        if(projectOptional.isEmpty())throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Project with ID: "+ projectID +" not found"); //Check if the project exists, send a message if not.
        projectRepository.deleteById(projectID); //deletes the project
    }

}
