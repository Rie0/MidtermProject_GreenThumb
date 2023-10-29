package com.GreenThumb.Mid.controller.impl;

import com.GreenThumb.Mid.model.Project;
import com.GreenThumb.Mid.model.ProjectType;
import com.GreenThumb.Mid.repository.ProjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ProjectControllerTest {
//    SETUP
    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    ProjectRepository projectRepository;

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    Project project;

    @BeforeEach
    public void setUP(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        project = new Project("RedSea Cleaning", ProjectType.Ocean_cleaning);
    }

    @AfterEach
    public void tearDown(){
        Project existingProject = projectRepository.findByProjectName("RedSea Cleaning");
        if (existingProject != null ) {
            projectRepository.delete(existingProject);
        }
    }

    @Test //GET TEST (Must run Project repository tests first for the IDs)
    void getAllProjects_validRequest_allProjects() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/api/projects"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains("1")); //ID's might need to be changed to an existing project's IDs
        assertTrue(mvcResult.getResponse().getContentAsString().contains("2"));
    }

    @Test //GET+POST TEST
    void getAllProjects_validRequest_checkProjectNamesInResponse() throws Exception {
        String body = objectMapper.writeValueAsString(project); //POST the new objects
        mockMvc.perform(post("/api/projects").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        MvcResult mvcResult = mockMvc.perform(get("/api/projects"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        String responseContent = mvcResult.getResponse().getContentAsString();

        assertTrue(responseContent.contains("RedSea Cleaning"));
    }

    @Test //POST TEST
    void saveProject_ValidProject_projectSaved() throws Exception {
        String body = objectMapper.writeValueAsString(project);

        mockMvc.perform(post("/api/projects").content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(projectRepository.findAll().toString().contains("RedSea Cleaning"));
    }

    @Test //DELETE TEST
    void deleteProject() throws Exception {
        projectRepository.save(project);
        String body = objectMapper.writeValueAsString(project);

        mockMvc.perform(delete("/api/projects/{projectID}", project.getProjectId())
                .content(body)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();

        assertFalse(projectRepository.findAll().toString().contains("RedSea Cleaning"));
    }



}