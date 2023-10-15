<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 10/15/2023
  Time: 2:46 PM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Upload DIMACS graph</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-container {
            max-width: 400px;
        }
    </style>
</head>
<body>
<div class="container form-container">
    <h1 class="text-center">Upload DIMACS formatted graph</h1>
    <form action="${pageContext.request.contextPath}/parse" method="POST" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="graphFile" class="form-label">Select a DIMACS file:</label>
            <input type="file" class="form-control" name="graphFile" id="graphFile" required>
        </div>
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" name="useEdges" id="useEdges">
            <label class="form-check-label" for="useEdges">Include edge number</label>
        </div>

        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" name="useVertices" id="useVertices">
            <label class="form-check-label" for="useVertices">Include vertices number</label>
        </div>
        <div class="text-center">
            <input type="submit" class="btn btn-primary" value="Upload and process">
        </div>
    </form>
</div>
</body>
</html>
