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
        
        validadeTitle(title);

        Task task = new Task(title);
        taskDAO.create(task);
    }

    public List<Task> getAllTasks() {
        return taskDAO.findAll();
    }

    public void deleteTask(long id) {
        
        validateId(id);
    
        taskDAO.delete(id);
    }

    public void updateTask(long id, String newTitle, boolean completed) {
        
        validateId(id);
        validadeTitle(newTitle);

        Task currentTask = taskDAO.findById(id);
        if(currentTask == null) {
            throw new IllegalArgumentException("Task not found");
        }

        Task updatedTask = new Task(id, newTitle, completed);
        taskDAO.update(updatedTask);
    }

    public void toggleTask(long id) {
        taskDAO.toggleCompleted(id);
    }


    private void validadeTitle(String title) {
        if(title == null || title.isBlank()) {
            throw new IllegalArgumentException("Task title can not be empty!");
        }

        if(title.length() > 100) {
            throw new IllegalArgumentException("Title can not be that long!");
        }
    }

    private void validateId(long id) {
        if(id < 1) throw new IllegalArgumentException("Invalid \"ID\"");
    }
}
