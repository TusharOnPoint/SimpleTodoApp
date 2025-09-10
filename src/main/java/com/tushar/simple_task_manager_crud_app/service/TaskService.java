package com.tushar.simple_task_manager_crud_app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tushar.simple_task_manager_crud_app.model.Task;

@Service
public class TaskService {
    private Map<Long, Task> taskRepo = new HashMap<>();
    private Long currentId = 1L;

    // create task
    public Task createTask(Task task) {
        task.setId(currentId);
        if (task.getStatus() == null || task.getStatus().isEmpty())
            task.setStatus("To Do");
        taskRepo.put(currentId, task);
        currentId++;
        return task;
    }

    // read all task
    public List<Task> getAllTask() {
        return new ArrayList<Task>(taskRepo.values());
    }

    // read task by id
    public Task getTask(Long id) {
        Task task = taskRepo.get(id);
        return task;
    }

    // update task
    public Task updateTask(Long id, Task upTask) {
        Task task = taskRepo.get(id);
        if (task != null) {
            if (upTask.getTitle() != null && !upTask.getTitle().isEmpty())
                task.setTitle(upTask.getTitle());
            if (upTask.getDescription() != null && !upTask.getDescription().isEmpty())
                task.setDescription(upTask.getDescription());
            if (upTask.getStatus() != null && !upTask.getStatus().isEmpty())
                task.setStatus(upTask.getStatus());
            return task;
        }
        return null;
    }

    // delete task
    public boolean deleteTask(Long id) {
        return taskRepo.remove(id) != null;
    }

    // filter by status
    public List<Task> getTasksByStatus(String status) {
        return taskRepo.values().stream()
                .filter(task -> task.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    // search by title/ description
    public List<Task> searchTasks(String keyword) {
        String lower = keyword.toLowerCase();
        return taskRepo.values().stream()
                .filter(task -> task.getTitle().toLowerCase().contains(lower) ||
                        (task.getDescription() != null && task.getDescription().toLowerCase().contains(lower)))
                .collect(Collectors.toList());
    }
}
