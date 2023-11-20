package com.GreenThumb.Mid.controller.impl;

import com.GreenThumb.Mid.controller.dto.ProjectNameDTO;
import com.GreenThumb.Mid.controller.interfaces.IProjectController;
import com.GreenThumb.Mid.model.Project;
import com.GreenThumb.Mid.repository.ProjectRepository;
import com.GreenThumb.Mid.service.impl.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectController implements IProjectController {

    @Autowired
    ProjectRepository projectRepository;//Grants access to data
    @Autowired
    ProjectService projectService;//Grant access to service methods

//  ***************************************************  GET  **********************************************************
    @GetMapping("/projects") //Using a get request get all the projects
    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }
    @GetMapping("/projects/{projectID}")//Get a project by ID
    public Project getProjectById(@PathVariable Integer projectID){
        return projectService.getProjectById(projectID);
    }

//  ****************************************************  POST  ********************************************************
    @PostMapping("/projects")//Post a new project
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProject(@RequestBody @Valid Project project){ //Add new project
        projectRepository.save(project);
    }
//  ****************************************************  PATCH  *******************************************************
    @PatchMapping("/projects/name/{projectID}")//Update a project name
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProjectName(@RequestBody @Valid ProjectNameDTO projectNameDTO,@PathVariable Integer projectID){
        projectService.updateProjectName(projectNameDTO.getProjectName(), projectID);


    }
//  **************************************************  DELETE  ********************************************************
    @DeleteMapping("/projects/{projectID}")//Delete a project
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProject(@PathVariable Integer projectID){
        projectService.deleteProject(projectID);
    }

}
