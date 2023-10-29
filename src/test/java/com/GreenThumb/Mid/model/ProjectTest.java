package com.GreenThumb.Mid.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    @Test
    void createProject() {
        Project project1 = Project.createProject("Ocean Cleaning Project 1", ProjectType.Ocean_cleaning);
        // Verify that the project's name and type match the provided values
        assertEquals("Ocean Cleaning Project 1", project1.getProjectName());
        assertEquals(ProjectType.Ocean_cleaning, project1.getProjectType());
    }

    @Test
    public void testAddProject() { //use the add method here to prevent saving information to the database..
        // Create a new volunteer and project
        VolunteerInfo info1 = new VolunteerInfo("john@example.com", "john@example.com");
        Volunteer volunteer = new Volunteer("John Doe", info1);
        Project project = Project.createProject("Tree Planting Project 1", ProjectType.Tree_Planting);

        // Call the addProject method to associate the volunteer with the project
        volunteer.addProject(project);

        // Verify that the volunteer's project is set to the specified project
        assertEquals(project, volunteer.getProject());

        System.out.println(volunteer.getProject());
    }
}