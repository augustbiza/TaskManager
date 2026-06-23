package com.taskmanager.service;

import com.taskmanager.dao.TaskDAO;
import com.taskmanager.model.Task;

import java.util.List;

public class TaskService {
    private TaskDAO taskDAO;

    public TaskService() {
        this.taskDAO = new TaskDAO();
    }

    public void createTask(String title) {
        if(title == null || title.isBlank()) {
            throw new IllegalArgumentException("Task can not be empty!");
        }

        Task task = new Task(title);
        taskDAO.create(task);
    }

    public List<Task> getAllTasks() {
        return taskDAO.findAll();
    }

    public void deleteTask(long id) {
        if(id < 1) throw new IllegalArgumentException("Invalid \"ID\"");
    
        taskDAO.delete(id);
    }

    public void toggleTask(long id) {
        taskDAO.toggleCompleted(id);
    }
}
