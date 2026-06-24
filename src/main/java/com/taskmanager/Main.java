package com.taskmanager;

import com.taskmanager.service.TaskService;
import com.taskmanager.dao.TaskDAO;
import com.taskmanager.model.Task;

import java.util.Scanner;
import java.util.List;

public class Main {

    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        
        TaskDAO dao = new TaskDAO();
        TaskService service = new TaskService(dao);

        String option;
        System.out.println("===== TASK MANAGER =====");
        
        do {
            System.out.println("Choose your option:\n1-List Tasks\n2-Search a task\n3-Create Task\n4-Update Task\n5-Delete Task\n6-Toggle Task\n0-Exit");
            option = scan.nextLine();
            long id;
            String title;
            List<Task> tasks = service.getAllTasks();
            switch(option) {
                case "0" :
                    System.out.println("========================");
                    break;
                case "1":
                    printTasks(tasks);

                    break;
                case "2":
                    System.out.print("Search by:\n1-Id\n2-Title\n: ");
                    String op = scan.nextLine();
                    Task t;
                    if(op.equals("1")) {
                        System.out.print("Id: ");
                        id = scan.nextLong(); scan.nextLine();
                        t = service.getTaskbyId(id);

                        if(t == null) {
                            System.out.println("Task not found");
                        } else {
                            printTask(t);
                        }
                    } else if(op.equals("2")) {
                        System.out.print("Title: ");
                        title = scan.nextLine();
                        t = service.getTaskByTitle(title);

                        if(t == null) {
                            System.out.println("Task not found");
                        } else {
                            printTask(t);
                        }
                    }
                    
                    break;
                case "3":
                    System.out.print("Title: ");
                    title = scan.nextLine();

                    service.createTask(title);
                    System.out.println("Task created!\n");

                    break;
                case "4":
                    System.out.print("Task ID: ");
                    id = scan.nextLong(); scan.nextLine();

                    System.out.print("New title: ");
                    String newTitle = scan.nextLine();

                    System.out.print("Completed (true/false): ");
                    String s = scan.nextLine();
                    boolean completed = (s.toLowerCase().equals("true") || s.toLowerCase().equals("t"));
                    service.updateTask(id, newTitle, completed);
                    System.out.println("Task updated!\n");

                    break;
                case "5":
                    System.out.print("Task ID: ");
                    id = scan.nextLong(); scan.nextLine();

                    System.out.println("Delete task " + id + "? (y/n): ");
                    String confirm = scan.nextLine();
                    if(confirm.toLowerCase().equals("y") || confirm.toLowerCase().equals("yes")) {
                        service.deleteTask(id);
                        System.out.println("Task deleted!\n");
                    }

                    break;
                case "6":
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

    private static void printTask(Task task) {
        System.out.print("[");
        if(task.isCompleted()) System.out.print("x] ");
        else System.out.print(" ] ");
        System.out.println(task.getId() + " - " + task.getTitle() + "\n");
    }

    private static void printTasks(List<Task> tasks) {
        for(Task t : tasks) {
            System.out.print("[");
            if(t.isCompleted()) System.out.print("x] ");
            else System.out.print(" ] ");
            System.out.println(t.getId() + " - " + t.getTitle());
        }
        System.out.println();
    }
}

/*
REDUZIR O ESCOPO DO SWITCH COLOCANDO O CODIGO EM FUNÇÕES SEPARADAS DA MAIN create()
*/