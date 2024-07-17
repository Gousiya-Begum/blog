<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Blog List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            padding: 20px;
            margin: 0;
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }
        .post {
            background-color: #fff;
            border: 1px solid #ccc;
            padding: 20px;
            margin-bottom: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .post h2 {
            color: #333;
            margin-bottom: 10px;
            font-size: 24px;
        }
        .post p {
            color: #666;
            line-height: 1.6;
            margin-bottom: 15px;
        }
        .post a {
            color: #007bff;
            text-decoration: none;
            display: inline-block;
            margin-top: 10px;
            font-weight: bold;
        }
        .post a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <h1>All Blog Posts</h1>

    <!-- Display the list of posts -->
    <c:forEach var="post" items="${posts}">
        <div class="post">
            <h2>${post.title}</h2>
            <p>${post.content}</p>
            <a href="viewPost?postId=${post.id}">View</a>
        </div>
    </c:forEach>
</body>
</html>
