package com.example.projectmanagement.project_management.controller;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.projectmanagement.project_management.model.Timesheet;
import com.example.projectmanagement.project_management.service.TimesheetService;

@RestController
@RequestMapping("/api/timesheets")
public class TimesheetController {

    private final TimesheetService timesheetService;

    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    @GetMapping("/task/{taskId}")
    public List<Timesheet> getTimesheetsByTask(@PathVariable Long taskId) {
        return timesheetService.getTimesheetsByTaskId(taskId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Timesheet> getTimesheetById(@PathVariable Long id) {
        return timesheetService.getTimesheetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Timesheet createTimesheet(@RequestBody Timesheet timesheet) {
        return timesheetService.createTimesheet(timesheet);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Timesheet> updateTimesheet(@PathVariable Long id, @RequestBody Timesheet updatedTimesheet) {
        try {
            Timesheet timesheet = timesheetService.updateTimesheet(id, updatedTimesheet);
            return ResponseEntity.ok(timesheet);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimesheet(@PathVariable Long id) {
        timesheetService.deleteTimesheet(id);
        return ResponseEntity.noContent().build();
    }
}
