<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Post</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
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

        .post-details {
            max-width: 600px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .post-details h2 {
            color: #333;
            margin-bottom: 10px;
        }

        .post-details p {
            color: #666;
            line-height: 1.6;
        }

        .post-details img {
            max-width: 100%;
            margin-top: 10px;
        }

        .post-details video {
            width: 100%;
            margin-top: 10px;
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
    <h1>View Post</h1>
    
    <c:choose>
        <c:when test="${empty post}">
            <p>Post not found.</p>
        </c:when>
        <c:otherwise>
            <div class="post-details">
                <h2>${post.title}</h2>
                <p>${post.content}</p>
                <p>Author: ${post.author}</p>
                <p>Posted on: ${post.postedAt}</p>
                <!-- Add more details as needed -->
                
                <c:if test="${not empty post.imageUrl}">
                    <img src="${post.imageUrl}" alt="Post Image">
                </c:if>

                <c:if test="${not empty post.videoUrl}">
                    <video controls>
                        <source src="${post.videoUrl}" type="video/mp4">
                        Your browser does not support the video tag.
                    </video>
                </c:if>
            </div>
        </c:otherwise>
    </c:choose>
    
    <p><a href="blogList.jsp">Back to Blog List</a></p>
</body>
</html>
