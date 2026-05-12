package com.student.taskmanager.controller;

import com.student.taskmanager.model.Task;
import com.student.taskmanager.model.User;
import com.student.taskmanager.service.TaskService;
import com.student.taskmanager.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final TaskService taskService;
    private final UserService userService;

    public AdminController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    // POST /admin/users -> Creates a new user (admin can set roles here later)
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // GET /admin/tasks -> Lists ALL tasks in the database
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    // DELETE /admin/tasks/{id} -> Deletes any task by its ID
    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}