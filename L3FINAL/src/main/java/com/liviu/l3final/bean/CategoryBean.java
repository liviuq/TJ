package com.liviu.l3final.bean;

import com.liviu.l3final.model.Category;
import com.liviu.l3final.service.CategoryService;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@ManagedBean
@ViewScoped
@Data
public class CategoryBean implements Serializable {
    @Resource(name = "L4Pool")
    private DataSource dataSource;
    private CategoryService categoryService;

    private Long category_id;
    private String name;

    private List<String> selectedCategories;

    @PostConstruct
    public void init() {
        // Instantiate the StudentService with the DataSource
        categoryService = new CategoryService(dataSource);
    }

    public List<Category> getAllCategories() {
        try {
            return categoryService.getAllCategories();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
