package com.example.projectmanagement.project_management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.projectmanagement.project_management.dto.ProjectReportDTO;
import com.example.projectmanagement.project_management.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<ProjectReportDTO> getProjectReport(@PathVariable Long projectId) {
        ProjectReportDTO report = reportService.generateProjectReport(projectId);
        return ResponseEntity.ok(report);
    }
}
