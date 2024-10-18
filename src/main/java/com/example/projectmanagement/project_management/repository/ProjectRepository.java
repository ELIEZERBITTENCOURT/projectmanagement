package com.example.projectmanagement.project_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectmanagement.project_management.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
