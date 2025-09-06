package com.tushar.simple_task_manager_crud_app.controller;

import com.tushar.simple_task_manager_crud_app.model.Task;
import com.tushar.simple_task_manager_crud_app.service.TaskService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @PostMapping("/new")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        logger.info("Creating new task: {}", task);
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTask() {
        logger.info("Fetching all tasks");
        return ResponseEntity.ok(taskService.getAllTask());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        logger.info("Fetching task with ID: {}", id);
        Task task = taskService.getTask(id);
        if(task!=null)
            return ResponseEntity.ok(task);
        else
            return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        logger.info("Updating task with ID: {}", id);
        Task upTask = taskService.updateTask(id, task);
        if(upTask!=null)
            return ResponseEntity.ok(upTask);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        logger.info("Deleting task with ID: {}", id);
        boolean deleted = taskService.deleteTask(id);
        if (deleted) {
            logger.info("Successfully deleted task with ID: {}", id);
            return ResponseEntity.noContent().build();
        } else {
            logger.warn("Task with ID: {} not found for deletion", id);
            return ResponseEntity.notFound().build();
        }
    }
}
