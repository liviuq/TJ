<jsp:useBean id="output" scope="request" type="com.liviu.l2.model.Output"/>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 10/15/2023
  Time: 2:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result of graph ${output.name}</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f4f4f4;
            padding: 20px;
        }
        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
        }
    </style>
</head>
<body>
<div class="container">
    <h1 class="display-4">Graph Properties</h1>
    <p class="lead">Name: ${output.name}</p>
    <% if(output.getUseVertices()) {%>
        <p class="lead">Number of Vertices (Order): ${output.vertices}</p>
    <%}%>
    <% if(output.getUseEdges()) {%>
        <p class="lead">Number of Edges (Size): ${output.edges}</p>
    <%}%>
</div>
</body>
</html>
