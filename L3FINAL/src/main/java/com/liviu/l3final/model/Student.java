package com.liviu.l3final.model;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class Student implements Serializable {
    private Long student_id;
    private String name;
    private List<Integer> projects;
}
