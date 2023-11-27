package com.todolist.demo.controllers;

import com.todolist.demo.exception.ResourceNotFoundException;
import com.todolist.demo.models.Task;
import com.todolist.demo.repositories.TaskRepository;
import com.todolist.demo.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @GetMapping
    public ResponseEntity<Object> getAllTasks() {
        List<Task> result = taskService.getAllTask();

        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Tasks available", HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/completed")
    public ResponseEntity<Object> getAllCompletedTasks() {
        List<Task> result = taskService.findAllCompletedTask();

        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Tasks completed", HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/incomplete")
    public ResponseEntity<Object> getAllIncompleteTasks() {
        List<Task> result = taskService.findAllInCompleteTask();

        if (!result.isEmpty()) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No Tasks completed", HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        return ResponseEntity.ok(taskService.createNewTask(task));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,@Valid @RequestBody Task task) {
        Task result = taskService.updateTask(id, task)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return ResponseEntity.ok(result);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        Task task = taskService.findTaskById(id).orElseThrow(() -> new ResourceNotFoundException(id));
       taskService.deleteTask(task);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }
}
