package com.tushar.simple_task_manager_crud_app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.tushar.simple_task_manager_crud_app.model.Task;

@Service
public class TaskService {
    private Map<Long, Task> taskRepo = new HashMap<>();
    private Long currentId = 1L;

    // create task
    public Task createTask(Task task) {
        task.setId(currentId++);
        if(task.getStatus()==null || task.getStatus().isEmpty())
            task.setStatus("To Do");
        taskRepo.put(currentId, task);
        return task;
    }

    // read all task
    public List<Task> getAllTask(){
        return new ArrayList<Task>(taskRepo.values());
    }

    // read task by id
    public Task getTask(Long id){
        Task task = taskRepo.get(id);
        return task;
    }

    // update task
    public Task updateTask(Long id, Task upTask){
        Task task = taskRepo.get(id);
        if(task!=null){
            task.setTitle(upTask.getTitle());
            task.setDiscription(upTask.getDiscription());
            task.setStatus(upTask.getStatus());
            return task;
        }
        return null;
    }
}
