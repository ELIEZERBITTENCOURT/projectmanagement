package com.example.projectmanagement.project_management.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.projectmanagement.project_management.model.Project;
import com.example.projectmanagement.project_management.repository.ProjectRepository;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project updatedProject) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setName(updatedProject.getName());
                    return projectRepository.save(project);
                })
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
