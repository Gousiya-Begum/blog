<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css"> <!-- Adjust the path to your CSS file -->

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

        .dashboard-links {
            margin-bottom: 20px;
        }

        .dashboard-links a {
            display: inline-block;
            margin-right: 15px;
            padding: 8px 16px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }

        .dashboard-links a:hover {
            background-color: #0056b3;
        }

        .post-item {
            background-color: #fff;
            border: 1px solid #ccc;
            padding: 15px;
            margin-bottom: 15px;
            border-radius: 5px;
        }

        .post-item h2 {
            color: #333;
            margin-bottom: 10px;
        }

        .post-item p {
            color: #666;
            line-height: 1.6;
        }

        .post-actions a {
            color: #007bff;
            text-decoration: none;
            margin-right: 10px;
        }

        .post-actions a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>Admin Dashboard</h1>

    <div class="dashboard-links">
        <a href="createPost">Create New Post</a>
        <a href="blogList.jsp">View All Posts</a>
    </div>

    <!-- Display the list of posts -->
    <c:forEach var="post" items="${posts}">
        <div class="post-item">
            <h2>${post.title}</h2>
            <p>${post.content}</p>
            <div class="post-actions">
                <a href="viewPost.jsp">View</a>
                <a href="updatePost.jsp">Update</a>
                <a href="delete.jsp">Delete</a>
            </div>
        </div>
    </c:forEach>

</body>
</html>
