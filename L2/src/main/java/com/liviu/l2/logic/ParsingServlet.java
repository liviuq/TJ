package com.liviu.l2.logic;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;

import com.liviu.l2.model.*;

@WebServlet(
        name = "parsingServlet",
        value = "/parse",
        initParams = {
                @WebInitParam(
                        name = "ParsingServlet",
                        value = "ParsingServlet"
                )
        })
@MultipartConfig
public class ParsingServlet extends HttpServlet {
    // create private fields Input and Output
    private Input input;
    private Output output;

    public void init() {
        // initialize the Input and Output fields
        input = new Input();
        output = new Output();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // here you can query for the default properties
        ServletContext context = getServletContext();
        String firstParam = (String) context.getAttribute("ParsingServlet");

        // set the response
        response.setContentType("text/html;charset=UTF-8");

        // retrieve the input file from the request
        Part filePart = request.getPart("graphFile");

        // set the input file
        input.setFilePart(filePart);



        // iterate through the parameters given
        for(String parameter : request.getParameterMap().keySet()) {
            if(parameter.equals("useEdges")) {
                output.setUseEdges(true);
                // create the cookie for the user
                Cookie cookie = new Cookie(parameter, "true");
                response.addCookie(cookie);
            }
            if(parameter.equals("useVertices")) {
                output.setUseVertices(true);
                // create the cookie for the user
                Cookie cookie = new Cookie(parameter, "true");
                response.addCookie(cookie);
            }
        }

        // set the input for the output
        output.setInput(input);

        // parse the input file
        output.parse();

        // add the output Bean to the request and forward it to result.jsp
        request.setAttribute("output", output);

        // forward the request and response to result.jsp
        getServletContext().getRequestDispatcher("/result.jsp").forward(request, response);
    }

    public void destroy() {
    }
}