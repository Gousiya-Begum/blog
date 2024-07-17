package com.blog.servlet;

import com.blog.dao.PostDao;
import com.blog.dao.PostDaoImpl;
import com.blog.model.Post;
import com.blog.util.FileUploadUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


@MultipartConfig
public class CreatePostServlet extends HttpServlet {

    private final PostDao postDao = new PostDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("createPost.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // Handle file upload (image/video)
        String imageUrl = FileUploadUtil.uploadFile(request, "image");
        String videoUrl = FileUploadUtil.uploadFile(request, "video");

        // Create a new post object
        Post newPost = new Post();
        newPost.setTitle(title);
        newPost.setContent(content);
        newPost.setImageUrl(imageUrl);
        newPost.setVideoUrl(videoUrl);

        // Set createdAt and updatedAt using current timestamp
        LocalDateTime createdAt = LocalDateTime.now();
        newPost.setCreatedAt(createdAt);
        newPost.setUpdatedAt(createdAt);  // Initially, createdAt and updatedAt are the same for a new post

        // Save the post using DAO
        boolean success = postDao.addPost(newPost);
        if (success) {
            response.sendRedirect(request.getContextPath() + "/adminDashboard");
        } else {
            request.setAttribute("error", "Failed to create post. Please try again.");
            request.getRequestDispatcher("createPost.jsp").forward(request, response);
        }
    }
}
