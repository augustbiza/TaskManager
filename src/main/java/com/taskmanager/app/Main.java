package com.taskmanager.app;

import com.taskmanager.database.DatabaseConnection;
import java.sql.Connection;
//import java.sql.SQLException;

public class Main {
    
    public static void main(String[] args) {
        
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver loaded");


            Connection connection = DatabaseConnection.getConnection();
            System.out.println("Connected succesfully");
            connection.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
