package com.liviu.l3final.model;

import com.liviu.l3final.database.DatabaseConnector;
import lombok.Data;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@ApplicationScoped
@Data
public class Project implements Serializable {
    private Long project_id;
    private String name;
    private String category;
    private String description;
    private Date deadline;

    // get all projects from the database
    public static List<Project> getAllProjects() throws ClassNotFoundException {
        Connection connection = DatabaseConnector.connect();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Project> projects = new ArrayList<>();
        if (connection != null) {
            try {
                String selectQuery = "SELECT * FROM projects";
                preparedStatement = connection.prepareStatement(selectQuery);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Project project = new Project();
                    project.setProject_id(resultSet.getLong("project_id"));
                    project.setName(resultSet.getString("name"));
                    project.setCategory(resultSet.getString("category"));
                    project.setDescription(resultSet.getString("description"));
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        project.setDeadline(df.parse(resultSet.getString("deadline")));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    projects.add(project);
                }
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return projects;
    }

    // add a project to the database
    public String submit() throws ClassNotFoundException {
        Connection connection = DatabaseConnector.connect();
        PreparedStatement preparedStatement = null;
        if (connection != null) {
            try {
                String insertQuery = "INSERT INTO projects (name, category, description, deadline) VALUES (?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, this.name);
                preparedStatement.setString(2, this.category);
                preparedStatement.setString(3, this.description);
                preparedStatement.setDate(4, new java.sql.Date(this.deadline.getTime()));
                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    return "success";
                }
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null; // This can be a navigation outcome if needed, e.g., "success"
    }
}
