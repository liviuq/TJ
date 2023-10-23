package com.liviu.l3final.bean;

import com.liviu.l3final.model.Project;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
@Data
public class ProjectBean implements Serializable {
    private List<Project> projects;

    @PostConstruct
    public void init() {
        try {
            projects = Project.getAllProjects();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
