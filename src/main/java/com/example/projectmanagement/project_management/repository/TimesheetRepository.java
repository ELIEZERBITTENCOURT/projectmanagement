package com.example.projectmanagement.project_management.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projectmanagement.project_management.model.Timesheet;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    List<Timesheet> findByTaskId(Long taskId);
}
