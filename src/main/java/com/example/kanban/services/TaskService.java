package com.example.kanban.services;

import com.example.kanban.model.Task;
import com.example.kanban.model.enums.Status;
import com.example.kanban.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        task.setStatus(Status.TO_DO);
        task.setCreatedAt(new java.util.Date());
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task moveTask(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();

            if (task.getStatus() == Status.TO_DO) {
                task.setStatus(Status.IN_PROGRESS);
            } else if (task.getStatus() == Status.IN_PROGRESS) {
                task.setStatus(Status.DONE);
            }

            return taskRepository.save(task);
        } else {
            throw new RuntimeException("Error");
        }
    }

    public Task updateTask(Long id, Task updatedTask) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setPriority(updatedTask.getPriority());
            task.setDueDate(updatedTask.getDueDate());
            return taskRepository.save(task);
        } else {
            throw new RuntimeException("Error");
        }
    }

    public void deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new RuntimeException("Error");
        }
    }
}
