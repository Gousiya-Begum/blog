<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Post</title>
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
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        label {
            display: block;
            margin-bottom: 10px;
            color: #333;
        }

        input[type="text"],
        textarea,
        input[type="file"] {
            width: calc(100% - 20px);
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 3px;
            font-size: 14px;
        }

        textarea {
            height: 150px; /* Adjust as needed */
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 3px;
            font-size: 16px;
        }

        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Update Post</h1>
    <form action="updatePost" method="post" enctype="multipart/form-data">
        <input type="hidden" name="postId" value="${post.id}">

        <label for="title">Title:</label>
        <input type="text" id="title" name="title" value="${post.title}" required><br>

        <label for="content">Content:</label>
        <textarea id="content" name="content" required>${post.content}</textarea><br>

        <label for="image">Image:</label>
        <input type="file" id="image" name="image"><br>

        <label for="video">Video:</label>
        <input type="file" id="video" name="video"><br>

        <input type="submit" value="Update Post">
    </form>
</body>
</html>
