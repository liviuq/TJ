package com.liviu.l2.model;

import jakarta.servlet.http.Part;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Output {
    // create private fields for the Input object
    private Input input;
    private String name;
    private Integer vertices;
    private Integer edges;
    private Boolean useVertices;
    private Boolean useEdges;

    public Output() {
        this.useEdges = false;
        this.useVertices = false;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVertices() {
        return vertices;
    }

    public void setVertices(Integer vertices) {
        this.vertices = vertices;
    }

    public Integer getEdges() {
        return edges;
    }

    public void setEdges(Integer edges) {
        this.edges = edges;
    }

    public Boolean getUseVertices() {
        return useVertices;
    }

    public void setUseVertices(Boolean useVertices) {
        this.useVertices = useVertices;
    }

    public Boolean getUseEdges() {
        return useEdges;
    }

    public void setUseEdges(Boolean useEdges) {
        this.useEdges = useEdges;
    }

    public void parse() throws IOException {
        // parse the input file
        Part filePart = input.getFilePart();

        // get the input stream
        InputStream input = filePart.getInputStream();

        // add all the lines to an array
        List<String> dimacsLines = readDIMACSData(input);

        // get the name
        setName(dimacsLines.get(0).split(" ")[2]);

        // retrieve the number of vertices and edges from the line that starts with "p"
        for (String line : dimacsLines) {
            if (line.startsWith("p")) {
                String[] parts = line.split("\\s+");
                this.setVertices(Integer.parseInt(parts[2]));
                this.setEdges(Integer.parseInt(parts[3]));
            }
        }
    }

    private List<String> readDIMACSData(InputStream inputStream) throws IOException {
        List<String> dimacsLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dimacsLines.add(line);
            }
        }
        return dimacsLines;
    }
}
