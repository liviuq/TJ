package com.liviu.l3final.bean;

import com.liviu.l3final.model.Student;
import com.liviu.l3final.service.StudentService;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Data
@ManagedBean
@SessionScoped
public class StudentBean implements Serializable {
    @Resource(name = "L4Pool")
    private DataSource dataSource;
    private StudentService studentService;
    private String name;
    private List<Long> selectedCategories;

    @PostConstruct
    public void init() {
        // Instantiate the StudentService with the DataSource
        studentService = new StudentService(dataSource);
    }

    public List<Student> getAllStudents() {
        try {
            return studentService.getAllStudents();
        } catch (ClassNotFoundException | SQLException e) {
            // Log the exception
            Logger.getLogger(ProjectBean.class.getName()).log(Level.SEVERE, "SQL error when getting all student", e);

            // Display a faces message for the user
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "SQL error when getting all student. Please try again later."));

            // Return an empty list
            return new ArrayList<>();
        }
    }

    public void submit() {
        try {
            if(!selectedCategories.isEmpty()) studentService.addStudent(name, selectedCategories);
        } catch (ClassNotFoundException | SQLException e) {
            // Log the exception
            Logger.getLogger(ProjectBean.class.getName()).log(Level.SEVERE, "SQL error when getting all student", e);

            // Display a faces message for the user
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "SQL error when getting all student. Please try again later."));
        }
    }

    public void deleteStudent(Long student_id) {
        try {
            studentService.deleteStudent(student_id);
        } catch (ClassNotFoundException | SQLException e) {
            // Log the exception
            Logger.getLogger(ProjectBean.class.getName()).log(Level.SEVERE, "SQL error when getting all student", e);

            // Display a faces message for the user
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "SQL error when getting all student. Please try again later."));
        }
    }
}
