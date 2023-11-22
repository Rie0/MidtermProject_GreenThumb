package com.GreenThumb.Mid.repository;

import com.GreenThumb.Mid.model.Project;
import com.GreenThumb.Mid.model.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    List<Project> findByProjectType(ProjectType projectType);
    Project findByProjectName(String projectName);
    void deleteByProjectName(String projectName);
}
