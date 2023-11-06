package com.liviu.l3final.bean;

import com.liviu.l3final.model.Project;
import com.liviu.l3final.service.ProjectService;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean
@SessionScoped
@Data
public class ProjectBean implements Serializable {

    @Resource(name = "L4Pool")
    private DataSource dataSource;
    private ProjectService projectService;

    private Long project_id;
    private String name;
    private Long category;
    private String description;
    private Date deadline;

    public ProjectBean() {
        projectService = new ProjectService(dataSource);
    }

    @PostConstruct
    public void init() {
        // Instantiate the StudentService with the DataSource
        projectService = new ProjectService(dataSource);
    }

    public List<Project> getAllProjects() {
        try {
            return projectService.getAllProjects();
        } catch (ClassNotFoundException | SQLException e) {
            // Log the exception
            Logger.getLogger(ProjectBean.class.getName()).log(Level.SEVERE, "An error occurred while retrieving projects", e);

            // Display a faces message for the user
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred while retrieving projects. Please try again later."));

            // Return an empty list or handle the error as needed
            return new ArrayList<>();
        }
    }

    public void deleteProject(Long project_id) {
        try {
            projectService.deleteProject(project_id);
        } catch (ClassNotFoundException | SQLException e) {
            // Log the exception
            Logger.getLogger(ProjectBean.class.getName()).log(Level.SEVERE, "An error occurred while deleting the project", e);

            // Display a faces message for the user
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred while deleting the project. Please try again later."));
        }
    }

    public void addProject() {
        projectService.addProject(this.name, this.category, this.description, this.deadline);
    }
}
