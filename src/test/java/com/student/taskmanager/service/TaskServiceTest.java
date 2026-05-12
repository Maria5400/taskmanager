package com.student.taskmanager.service;

import com.student.taskmanager.model.Task;
import com.student.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock // This creates a fake database repository
    private TaskRepository taskRepository;

    @InjectMocks // This injects the fake repository into our real service
    private TaskService taskService;

    @Test
    public void testCreateTask() {
        Task task = new Task();
        task.setTitle("Test Task");

        // Tell the fake database what to do when someone tries to save
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.createTask(task);
        assertEquals("Test Task", result.getTitle()); // Assert that it worked!
    }

    @Test
    public void testGetMyTasks() {
        Task task = new Task();
        task.setTitle("User Task");

        // Tell the fake database to return a list with our task when asked for User ID 1
        when(taskRepository.findByOwnerUserId(1L)).thenReturn(List.of(task));

        List<Task> results = taskService.getMyTasks(1L);
        assertEquals(1, results.size());
    }
}