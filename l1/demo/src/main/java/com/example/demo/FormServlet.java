package com.example.demo;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "formServlet", value = "/form")
public class FormServlet extends HttpServlet {

    public void init() throws ServletException { }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Send a response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Form for the number of vertices</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action=\"/generate-tree\" method=\"GET\">");
        out.println("<label for=\"field\">Field:</label>");
        out.println("<input type=\"text\" id=\"numVertices\" name=\"numVertices\" placeholder=\"Enter the number of vertices\">");
        out.println("<br>");
        out.println("<button type=\"submit\">Submit</button>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
}
