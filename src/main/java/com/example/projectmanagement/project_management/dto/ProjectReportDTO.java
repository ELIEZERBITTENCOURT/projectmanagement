package com.example.projectmanagement.project_management.dto;

import java.util.List;

public class ProjectReportDTO {

    private Long projectId;
    private String projectName;
    private List<TaskReportDTO> tasks;
    private long totalTimeSpent; // em minutos

    // Getters and Setters
    
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getTotalTimeSpent() {
        return totalTimeSpent;
    }

    public void setTotalTimeSpent(Long totalTimeSpent) {
        this.totalTimeSpent = totalTimeSpent;
    }

    public List<TaskReportDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskReportDTO> tasks) {
        this.tasks = tasks;
    }
}
