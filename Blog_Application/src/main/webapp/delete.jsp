<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Post</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        form {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        p {
            margin-bottom: 10px;
        }

        button[type="submit"] {
            padding: 8px 16px;
            background-color: #dc3545;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button[type="submit"]:hover {
            background-color: #c82333;
        }

        a {
            display: inline-block;
            margin-top: 10px;
            text-decoration: none;
            color: #007bff;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Delete Post</h1>
    
    <%-- Display error message if any --%>
    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>
    
    <form action="${pageContext.request.contextPath}/deletePost" method="post">
        <input type="hidden" name="postId" value="${param.postId}">
        
        <p>Are you sure you want to delete this post?</p>
        <p><strong>Title:</strong> ${param.title}</p>
        <p><strong>Content:</strong> ${param.content}</p>
        
        <button type="submit">Confirm Delete</button>
        <a href="adminDashboard.jsp">Cancel</a>
    </form>
</body>
</html>
