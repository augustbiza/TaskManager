package com.taskmanager;

import com.taskmanager.service.TaskService;

import java.util.Scanner;

public class Main {

    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        
        TaskService service = new TaskService();

        String option;
        System.out.println("===== TASK MANAGER =====");
        
        do {
            System.out.println("Choose your option:\n1-List Tasks\n2-Create Task\n3-Update Task\n4-Delete Task\n0-Exit");
            option = scan.nextLine();
            long id;
            String title;
            switch(option) {
                case "0" :
                    System.out.println("========================");
                    break;
                case "1":
                    System.out.println("Tasks: ");
                    service.getAllTasks().forEach(System.out::println);
                    System.out.println();
                    
                    break;
                case "2":
                    System.out.print("Title: ");
                    title = scan.nextLine();

                    service.createTask(title);
                    System.out.println("Task created!\n");

                    break;
                case "3":
                    service.getAllTasks().forEach(System.out::println);

                    System.out.print("Task ID: ");
                    id = scan.nextLong(); scan.nextLine();

                    System.out.print("New title: ");
                    String newTitle = scan.nextLine();

                    System.out.print("Completed (true/false): ");
                    boolean completed = scan.nextBoolean(); scan.nextLine();

                    service.updateTask(id, newTitle, completed);
                    System.out.println("Task updated!\n");

                    break;
                case "4":
                    service.getAllTasks().forEach(System.out::println);

                    System.out.print("Task ID: ");
                    id = scan.nextLong(); scan.nextLine();

                    service.deleteTask(id);
                    System.out.println("Task deleted!\n");

                    break;
                case "5":
                    service.getAllTasks().forEach(System.out::println);

                    System.out.println("Task ID: ");
                    id = scan.nextLong(); scan.nextLine();

                    service.toggleTask(id);
                    System.out.println("Task updated!\n");

                    break;
                default:
                    System.out.println("Invalid Option\n");
                    break;
            }
        } while(!option.equals("0"));
    }
}
