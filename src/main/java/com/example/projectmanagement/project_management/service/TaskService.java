package com.example.projectmanagement.project_management.service;

import java.util.*;

import org.springframework.stereotype.Service;

import com.example.projectmanagement.project_management.model.Task;
import com.example.projectmanagement.project_management.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }
    
    public Task updateTask(Long id, Task updatedTask) {
        return taskRepository.findById(id)
            .map(task -> {
                task.setDescription(updatedTask.getDescription());
                task.setProject(updatedTask.getProject()); 
                return taskRepository.save(task);
            })
            .orElseThrow(() -> new RuntimeException("Task não encontrada"));
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
