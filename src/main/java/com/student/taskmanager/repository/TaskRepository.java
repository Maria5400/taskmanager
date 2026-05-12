package com.student.taskmanager.repository;

import com.student.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // This will magically find all tasks belonging to a specific user
    List<Task> findByOwnerUserId(Long ownerUserId);
}