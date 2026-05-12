package com.student.taskmanager.controller;

import com.student.taskmanager.model.Task;
import com.student.taskmanager.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // POST /tasks -> Creates a new task
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    // GET /tasks/my -> Gets tasks for a specific user
    // (Note: We use @RequestParam for now to test it. In the next step, Spring Security will do this automatically!)
    @GetMapping("/my")
    public List<Task> getMyTasks(@RequestParam Long userId) {
        return taskService.getMyTasks(userId);
    }
}