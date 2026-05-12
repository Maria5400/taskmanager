package com.student.taskmanager.service;

import com.student.taskmanager.model.Task;
import com.student.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // This tells Spring Boot "Hey, this is a brain class, keep it ready!"
public class TaskService {

    private final TaskRepository taskRepository;

    // This is called Dependency Injection. We are plugging the repository into our service.
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getMyTasks(Long userId) {
        return taskRepository.findByOwnerUserId(userId);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}