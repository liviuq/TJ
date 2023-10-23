package com.liviu.l3final.model;

import com.liviu.l3final.database.DatabaseConnector;
import lombok.Data;

import javax.faces.bean.ApplicationScoped;
import javax.persistence.Entity;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@ApplicationScoped
@Data
public class Student implements Serializable {
    private String name;
    private List<Integer> projects;

    // get all Students from the db
    // get all projects from the database
    public static List<Student> getAllStudents() throws ClassNotFoundException {
        Connection connection = DatabaseConnector.connect();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Student> students = new ArrayList<>();
        if (connection != null) {
            try {
                String selectQuery = "SELECT * FROM students";
                preparedStatement = connection.prepareStatement(selectQuery);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Student student = new Student();
                    student.setName(resultSet.getString("name"));
                    Array arr = resultSet.getArray("projects_ids"); //problems here
                    List<Integer> temp = new ArrayList<>();
                    for (value : arr) {
                        temp.add(value);
                    }
                    student.setProjects((List<Integer>) arr.getArray());
                    students.add(student);
                }
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return students;
    }

    // submit
    public String submit() throws ClassNotFoundException {
        Connection connection = DatabaseConnector.connect();
        PreparedStatement preparedStatement = null;
        if (connection != null) {
            try {
                String insertQuery = "INSERT INTO students (name, projects_ids) VALUES (?, ?)";
                preparedStatement = connection.prepareStatement(insertQuery);
                preparedStatement.setString(1, this.name);
                preparedStatement.setArray(2, connection.createArrayOf("INT", projects.toArray()));
                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    // Insert successful
                    return "success"; // Navigation outcome if needed
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
