package com.liviu.l3final.service;


import com.liviu.l3final.bean.ProjectBean;
import com.liviu.l3final.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Data
@AllArgsConstructor
public class StudentService {
    private DataSource dataSource;

    public List<Student> getAllStudents() throws ClassNotFoundException, SQLException {
        List<Student> students = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String selectQuery = "SELECT * FROM students";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Student student = new Student();
                student.setStudent_id(resultSet.getLong("student_id"));
                student.setName(resultSet.getString("name"));
                Array projectIdsArray = resultSet.getArray("projects_ids");

                if (projectIdsArray != null) {
                    Integer[] projectIds = (Integer[]) projectIdsArray.getArray();
                    List<Integer> projectIdsList = Arrays.asList(projectIds);
                    student.setProjects(new ArrayList<>(projectIdsList));
                }
                students.add(student);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            // Log the exception
            Logger.getLogger(ProjectBean.class.getName()).log(Level.SEVERE, "SQL error when getting all student", e);

            // Display a faces message for the user
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "SQL error when getting all student. Please try again later."));
        }
        return students;
    }

    public void addStudent(String name, List<Long> projectIds) throws ClassNotFoundException, SQLException {
        try (Connection connection = dataSource.getConnection()){
            String insertQuery = "INSERT INTO students (name, projects_ids) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setArray(2, connection.createArrayOf("INT", projectIds.toArray()));

            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            // Log the exception
            Logger.getLogger(ProjectBean.class.getName()).log(Level.SEVERE, "SQL error when adding a student", e);

            // Display a faces message for the user
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "SQL error when adding a student. Please try again later."));
        }
    }

    public void deleteStudent(Long id) throws ClassNotFoundException, SQLException {
        try (Connection connection = dataSource.getConnection()){
            String deleteQuery = "DELETE FROM students WHERE student_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            // Log the exception
            Logger.getLogger(ProjectBean.class.getName()).log(Level.SEVERE, "SQL error when deleting a student", e);

            // Display a faces message for the user
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "SQL error when deleting a student. Please try again later."));
        }
    }
}