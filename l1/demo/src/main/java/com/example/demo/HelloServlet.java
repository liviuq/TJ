package com.example.demo;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    public void init() {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        int numVertices = 0;
        // extract the number from the request
        try {
            numVertices = Integer.parseInt(request.getParameter("numVertices"));
        }
        catch (NumberFormatException ignored) {}

        // create an ordered list of the digits of the number
        List<String> number_deconstructed = Arrays.asList(String.valueOf(numVertices).split(""));

        // sort number_deconstructed
        Collections.sort(number_deconstructed);

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + number_deconstructed.toString() + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}