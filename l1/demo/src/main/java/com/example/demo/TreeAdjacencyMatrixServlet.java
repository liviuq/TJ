package com.example.demo;

import java.io.*;
import java.util.Random;
import java.util.logging.Logger;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "treeAdjacencyMatrixServlet", value = "/generate-tree")
public class TreeAdjacencyMatrixServlet extends HttpServlet {

    // declare the logger for this class
    private static final Logger logger = Logger.getLogger(TreeAdjacencyMatrixServlet.class.getName());

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // set the returned content type
        response.setContentType("text/html");

        int numVertices = 0;
        // extract the number from the request
        try {
            numVertices = Integer.parseInt(request.getParameter("numVertices"));
        } catch (NumberFormatException ignored) {
        }

        // create a PrintWriter to send the response back
        PrintWriter out = response.getWriter();

        // generate a random tree adjacency matrix
        int[][] adjacencyMatrix = generateRandomTree(numVertices);

        // return the adjacency matrix as a JSON object if the parameter application is True
        if (request.getParameter("application") != null) {
            response.setContentType("application/json");
            out.println("{");
            out.println("\"numVertices\": " + numVertices + ",");
            out.println("\"adjacencyMatrix\": [");
            for (int i = 0; i < numVertices; i++) {
                out.println("[");
                for (int j = 0; j < numVertices; j++) {
                    out.println(adjacencyMatrix[i][j] + ",");
                }
                out.println("],");
            }
            out.println("]");
            out.println("}");
            return;
        }
        // build the html table
        out.println("<html>");
        out.println("<head><title>Random Tree</title></head>");
        out.println("<body>");
        out.println("<h1>Random Tree - Adjacency Matrix</h1>");
        out.println("<p>Number of Vertices: " + numVertices + "</p>");
        out.println("<table border='1'>");

        // Output the adjacency matrix as an HTML table
        for (int i = 0; i < numVertices; i++) {
            out.println("<tr>");
            for (int j = 0; j < numVertices; j++) {
                out.println("<td>" + adjacencyMatrix[i][j] + "</td>");
            }
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");

        // gather the logs for the request
        //HTTP method used
        String method = request.getMethod();
        logger.info("HTTP method: " + method);
        logger.log(java.util.logging.Level.INFO, "HTTP method: {0}", method);

        //IP address of the client
        String ipAddress = request.getRemoteAddr();
        logger.info("IP address: " + ipAddress);

        //User-Agent header
        String userAgent = request.getHeader("User-Agent");
        logger.info("User-Agent header: " + userAgent);

        //Client language
        String language = request.getHeader("Accept-Language");
        logger.info("Accept-Language header: " + language);

        //Parameters sent with the request
        String parameters = request.getQueryString();
        logger.info("Parameters: " + parameters);
    }

    // Function to generate a random tree adjacency matrix
    private int[][] generateRandomTree(int numVertices) {
        Random rand = new Random();
        int[][] adjacencyMatrix = new int[numVertices][numVertices];

        for (int i = numVertices - 1; i < numVertices * 2; i++) {
            int parent = rand.nextInt(numVertices);
            int child = rand.nextInt(numVertices);


            while (parent == child || adjacencyMatrix[parent][child] == 1) {
                parent = rand.nextInt(numVertices);
                child = rand.nextInt(numVertices);
            }

            if(!createsCycle(adjacencyMatrix, numVertices, parent, child)){
                adjacencyMatrix[parent][child] = 1;
                adjacencyMatrix[child][parent] = 1;
            }
        }

        return adjacencyMatrix;
    }

    private boolean createsCycle(int[][] adjacencyMatrix, int numVertices, int parent, int child) {
        boolean[] visited = new boolean[numVertices];
        visited[parent] = true;
        return dfs(adjacencyMatrix, numVertices, child, visited, parent);
    }

    private boolean dfs(int[][] adjacencyMatrix, int numVertices, int current, boolean[] visited, int parent) {

        visited[current] = true;
        for (int i = 0; i < numVertices; i++) {
            if (adjacencyMatrix[current][i] == 1) {
                if (!visited[i]) {
                    if (dfs(adjacencyMatrix, numVertices, i, visited, current)) {
                        return true;
                    }
                } else if (i != parent) {
                    return true;
                }
            }
        }
        return false;
    }
}
