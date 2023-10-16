package com.liviu.l2.listener;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class DefaultPropertiesContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        // Read default properties from context init parameters
        String defaultValue = ((ServletContext) servletContext).getInitParameter("defaultPropertyValue");

        // Store the default property value as an application attribute
        servletContext.setAttribute("defaultPropertyValue", defaultValue);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // Cleanup code if needed
    }
}