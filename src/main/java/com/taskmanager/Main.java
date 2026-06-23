package com.taskmanager;

import com.taskmanager.service.TaskService;

public class Main {
    public static void main(String[] args) {
        TaskService service = new TaskService();

        service.toggleTask(7);
    }
}
