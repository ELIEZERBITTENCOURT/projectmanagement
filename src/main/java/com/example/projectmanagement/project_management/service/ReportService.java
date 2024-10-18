package com.example.projectmanagement.project_management.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.projectmanagement.project_management.dto.ProjectReportDTO;
import com.example.projectmanagement.project_management.dto.TaskReportDTO;
import com.example.projectmanagement.project_management.model.Project;
import com.example.projectmanagement.project_management.model.Timesheet;
import com.example.projectmanagement.project_management.repository.ProjectRepository;
import com.example.projectmanagement.project_management.repository.TimesheetRepository;

@Service
public class ReportService {

    private final ProjectRepository projectRepository;
    private final TimesheetRepository timesheetRepository;

    public ReportService(ProjectRepository projectRepository, TimesheetRepository timesheetRepository) {
        this.projectRepository = projectRepository;
        this.timesheetRepository = timesheetRepository;
    }

    public ProjectReportDTO generateProjectReport(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        List<TaskReportDTO> taskReports = project.getTasks().stream().map(task -> {
            List<Timesheet> timesheets = timesheetRepository.findByTaskId(task.getId());
            long timeSpent = timesheets.stream()
                    .mapToLong(timesheet -> timesheet.getEndTime().minusMinutes(timesheet.getStartTime().getMinute()).getMinute())
                    .sum();

            TaskReportDTO taskReport = new TaskReportDTO();
            taskReport.setTaskDescription(task.getDescription());
            taskReport.setTimeSpent(timeSpent);
            return taskReport;
        }).collect(Collectors.toList());

        long totalTimeSpent = taskReports.stream().mapToLong(TaskReportDTO::getTimeSpent).sum();

        ProjectReportDTO projectReport = new ProjectReportDTO();
        projectReport.setProjectName(project.getName());
        projectReport.setTasks(taskReports);
        projectReport.setTotalTimeSpent(totalTimeSpent);

        return projectReport;
    }
}
