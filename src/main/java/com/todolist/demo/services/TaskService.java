package com.todolist.demo.services;

import com.todolist.demo.models.Task;
import com.todolist.demo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task createNewTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public Optional<Task> findTaskById(Long id) {
        Optional<Task> result = taskRepository.findById(id);
        return result;
    }

    public List<Task> findAllCompletedTask() {
        return taskRepository.findByCompletedTrue();
    }

    public List<Task> findAllInCompleteTask() {
        return taskRepository.findByCompletedFalse();
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public Optional<Task> updateTask(Long id, Task task) {
        Optional<Task> result = taskRepository.findById(id);
        try{
            Task temp = result.get();
            temp.setTask(task.getTask());
            return Optional.of(taskRepository.save(temp));
        }catch(Exception e){
            return result;
        }
    }

    public void deleteTask(Long id) {
    }
}