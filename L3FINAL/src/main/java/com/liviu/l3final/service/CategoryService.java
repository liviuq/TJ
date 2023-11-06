package com.liviu.l3final.service;

import com.liviu.l3final.bean.ProjectBean;
import com.liviu.l3final.model.Category;
import com.liviu.l3final.model.Student;
import lombok.AllArgsConstructor;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@ManagedBean
@ViewScoped
@AllArgsConstructor
public class CategoryService {
    private DataSource dataSource;

    public List<Category> getAllCategories() throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Category> categories = new ArrayList<>();

        if (connection != null) {
            try {
                String selectQuery = "SELECT * FROM categories";
                preparedStatement = connection.prepareStatement(selectQuery);
                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Category category = new Category();
                    category.setCategory_id(resultSet.getLong("category_id"));
                    category.setName(resultSet.getString("name"));

                    categories.add(category);
                }
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                // Log the exception
                Logger.getLogger(ProjectBean.class.getName()).log(Level.SEVERE, "SQL error when getting all the categories", e);

                // Display a faces message for the user
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "SQL error when getting all the categories. Please try again later."));
            }
        }
        return categories;
    }
}
