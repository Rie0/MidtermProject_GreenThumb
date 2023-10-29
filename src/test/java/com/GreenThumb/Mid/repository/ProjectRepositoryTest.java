package com.GreenThumb.Mid.repository;

import com.GreenThumb.Mid.model.Project;
import com.GreenThumb.Mid.model.ProjectType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProjectRepositoryTest {
    @Autowired
    private ProjectRepository projectRepository;

    @BeforeEach
    public void setUp(){ //Creates new projects
      Project project1 = new Project("Test Project 1",ProjectType.Ocean_cleaning);
      Project project2 = new Project("Test Project 2", ProjectType.Tree_Planting);
      projectRepository.saveAll(List.of(project1,project2));//saves to the database
    }
    //@AfterEach
    //public void tearDown(){ //Clears new projects
        //projectRepository.deleteAll();
    //}

    @Test
    public void FindAll_Projects_projectList() {
        // Retrieve all projects from the repository
        List<Project> projectsList = projectRepository.findAll();
        System.out.println(projectsList);
        // Ensure that the list contains two projects
        assertEquals(2,projectsList.size()); //number might change after adding projects multiple times
    }

    @Test
    public void findByProjectType_projectType_projectList() {
        // Retrieve projects by project type (e.g., ProjectType.Ocean_cleaning)
        List<Project> projectList = projectRepository.findByProjectType(ProjectType.Tree_Planting);
        // Ensure that the list is not null and contains at least one project
        assertEquals(1,projectList.size());

    }
}