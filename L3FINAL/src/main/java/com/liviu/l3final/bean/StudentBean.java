package com.liviu.l3final.bean;

import com.liviu.l3final.model.Project;
import com.liviu.l3final.model.Student;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
@Data
public class StudentBean implements Serializable {
    private List<Student> students;

    @PostConstruct
    public void init() {
        try {
            students = Student.getAllStudents();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(Long student_id) {
        if (student_id != null) {
            try {
                Student.deleteStudent(student_id);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
