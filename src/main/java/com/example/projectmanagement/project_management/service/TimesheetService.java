package com.example.projectmanagement.project_management.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.projectmanagement.project_management.model.Timesheet;
import com.example.projectmanagement.project_management.repository.TimesheetRepository;

@Service
public class TimesheetService {

    private final TimesheetRepository timesheetRepository;

    public TimesheetService(TimesheetRepository timesheetRepository) {
        this.timesheetRepository = timesheetRepository;
    }

    public List<Timesheet> getTimesheetsByTaskId(Long taskId) {
        return timesheetRepository.findByTaskId(taskId);
    }

    public Optional<Timesheet> getTimesheetById(Long id) {
        return timesheetRepository.findById(id);
    }

    public Timesheet createTimesheet(Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }

    public Timesheet updateTimesheet(Long id, Timesheet updatedTimesheet) {
        return timesheetRepository.findById(id)
                .map(timesheet -> {
                    timesheet.setStartTime(updatedTimesheet.getStartTime());
                    timesheet.setEndTime(updatedTimesheet.getEndTime());
                    timesheet.setDescription(updatedTimesheet.getDescription());
                    return timesheetRepository.save(timesheet);
                }).orElseThrow(() -> new RuntimeException("Timesheet não encontrado"));
    }

    public void deleteTimesheet(Long id) {
        timesheetRepository.deleteById(id);
    }
}
