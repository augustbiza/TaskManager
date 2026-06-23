package com.taskmanager.model;

public class Task {
    
    private long id;
    private String title;
    private boolean completed;

    public long getId() { return id; }
    public String getTitle() { return title; }
    public boolean isCompleted() { return completed; }

    public void setTitle(String title) { this.title = title; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public Task(String title) {
        this.title = title;
        this.completed = false;
    }
    public Task(long id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Id: " + id + " | Title: " + title + " | Completed: " + completed;
    }
}
