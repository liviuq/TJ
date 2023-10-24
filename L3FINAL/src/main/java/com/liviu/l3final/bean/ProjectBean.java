package com.liviu.l3final.bean;

import com.liviu.l3final.model.Project;
import lombok.Data;
import org.primefaces.event.CellEditEvent;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.swing.text.html.parser.Entity;
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

    public void deleteProject(Long project_id) {
        if (project_id != null) {
            try {
                Project.deleteProject(project_id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
