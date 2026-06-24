package com.taskmanager.dao;

import com.taskmanager.database.DatabaseConnection;

import com.taskmanager.model.Task;

import java.sql.*;
import java.util.List;

import javax.management.RuntimeErrorException;

import java.util.ArrayList;

public class TaskDAO {
   
    public void create(Task task) {
        String sql = "INSERT INTO tasks (title, completed) VALUES (?, ?)";

        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            sm.setString(1, task.getTitle());
            sm.setBoolean(2, task.isCompleted());

            sm.executeUpdate();

        } catch(SQLException e) {
            throw new DAOException("Error inserting task", e);
        }
    }

    public Task findById(long id) {
        String sql = "SELECT * FROM tasks WHERE id = ?";

        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            sm.setLong(1, id);

            ResultSet rs = sm.executeQuery();

            if(rs.next()) {
                return new Task(rs.getLong("id"), rs.getString("title"), rs.getBoolean("completed"));
            }

        } catch(SQLException e) {
            throw new DAOException("Error finding task", e);
        }

        return null;
    }

    public Task findByTitle(String title) {
        String sql = "SELECT * FROM tasks WHERE title = ?";

        try(Connection conn = DatabaseConnection.getConnection();
        PreparedStatement sm = conn.prepareStatement(sql)) {
            sm.setString(1, title);

            ResultSet rs = sm.executeQuery();
            if(rs.next()) {
                return new Task(rs.getLong("id"), rs.getString("title"), rs.getBoolean("completed"));
            }
        } catch(SQLException e) {
            throw new DAOException("Error finding task", e);
        }

        return null;
    }

    public List<Task> findAll() {
        String sql = "SELECT * FROM tasks ORDER BY id";

        List<Task> tasks = new ArrayList<>();

        try(Connection conn = DatabaseConnection.getConnection();
            Statement sm = conn.createStatement();
            ResultSet rs = sm.executeQuery(sql)) {
                while (rs.next()) {
                    tasks.add(new Task(rs.getLong("id"), rs.getString("title"), rs.getBoolean("completed")));
                }
        } catch(SQLException e) {
            throw new DAOException("Error listing tasks", e);
        }

        return tasks;
    }

    public void update(Task task) {
        String sql = "UPDATE tasks SET title = ?, completed = ? WHERE id = ?";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement sm = conn.prepareStatement(sql);) {
                
            sm.setString(1, task.getTitle());
            sm.setBoolean(2, task.isCompleted());
            sm.setLong(3, task.getId());

            sm.executeUpdate();
        } catch(SQLException e) {
            throw new DAOException("Error updating task", e);
        }
    }

    public void delete(long id) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement sm = conn.prepareStatement(sql)) {

            sm.setLong(1, id);
            sm.executeUpdate();
        } catch(SQLException e) {
            throw new DAOException("Error deleting task", e);
        }
    }

    public void toggleCompleted(long id) {
        String sql = "UPDATE tasks SET completed = NOT completed WHERE id = ?";

        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement sm = conn.prepareStatement(sql)) {

            sm.setLong(1, id);
            sm.executeUpdate();
        } catch(SQLException e) {
            throw new DAOException("Error toggling task", e);
        }
    }

}
