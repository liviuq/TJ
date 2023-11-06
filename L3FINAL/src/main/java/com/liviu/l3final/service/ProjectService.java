package com.liviu.l3final.service;

import com.liviu.l3final.bean.ProjectBean;
import com.liviu.l3final.model.Project;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Data
@AllArgsConstructor
public class ProjectService {
    private DataSource dataSource;

    public List<Project> getAllProjects() throws ClassNotFoundException, SQLException {
        List<Project> projects = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()){
            String selectQuery = "SELECT * FROM projects";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Project project = new Project();
                project.setProject_id(resultSet.getLong("project_id"));
                project.setName(resultSet.getString("name"));
                project.setCategory(resultSet.getString("category"));
                project.setDescription(resultSet.getString("description"));
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                project.setDeadline(df.parse(resultSet.getString("deadline")));

                projects.add(project);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new SQLException("Couldn't process th SQL request");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return projects;
    }

    public void deleteProject(Long project_id) throws ClassNotFoundException, SQLException {
        try(Connection connection = dataSource.getConnection()) {
            String deleteQuery = "DELETE FROM projects WHERE project_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setLong(1, project_id);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            // Log the exception
            Logger.getLogger(ProjectBean.class.getName()).log(Level.SEVERE, "SQL error when deleting a project", e);

            // Display a faces message for the user
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "SQL error when deleting a project. Please try again later."));
        }
    }

    public void addProject(String name, Long category, String description, Date deadline) {
        try (Connection connection = dataSource.getConnection()) {

            String insertQuery = "INSERT INTO projects (name, category, description, deadline) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, name);
                preparedStatement.setLong(2, category);
                preparedStatement.setString(3, description);
                preparedStatement.setDate(4, new java.sql.Date(deadline.getTime()));
                preparedStatement.executeUpdate();
            }


        } catch (SQLException e) {
            // Log the exception
            Logger.getLogger(ProjectBean.class.getName()).log(Level.SEVERE, "SQL error when inserting a project", e);

            // Display a faces message for the user
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "SQL error when inserting a project. Please try again later."));
        }
    }
}
