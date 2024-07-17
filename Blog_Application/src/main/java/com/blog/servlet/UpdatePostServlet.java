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
public class UpdatePostServlet extends HttpServlet {

    private final PostDao postDao = new PostDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int postId = Integer.parseInt(request.getParameter("postId"));
            Post post = postDao.getPostById(postId);

            if (post == null) {
                response.sendRedirect(request.getContextPath() + "/adminDashboard");
            } else {
                request.setAttribute("post", post);
                request.getRequestDispatcher("updatePost.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/adminDashboard");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int postId = Integer.parseInt(request.getParameter("postId"));
            String title = request.getParameter("title");
            String content = request.getParameter("content");

            Post existingPost = postDao.getPostById(postId);
            if (existingPost == null) {
                request.setAttribute("error", "Post not found.");
                request.getRequestDispatcher("updatePost.jsp").forward(request, response);
                return;
            }

            
            String imageUrl = FileUploadUtil.uploadFile(request, "image");
            String videoUrl = FileUploadUtil.uploadFile(request, "video");

            if (imageUrl == null || imageUrl.isEmpty()) {
                imageUrl = existingPost.getImageUrl();
            }
            if (videoUrl == null || videoUrl.isEmpty()) {
                videoUrl = existingPost.getVideoUrl();
            }

            Post updatedPost = new Post();
            updatedPost.setId(postId);
            updatedPost.setTitle(title);
            updatedPost.setContent(content);
            updatedPost.setImageUrl(imageUrl);
            updatedPost.setVideoUrl(videoUrl);
            updatedPost.setCreatedAt(existingPost.getCreatedAt());
            updatedPost.setUpdatedAt(LocalDateTime.now());

            boolean success = postDao.updatePost(updatedPost);
            if (success) {
                response.sendRedirect(request.getContextPath() + "/adminDashboard?message=Post updated successfully");
            } else {
                request.setAttribute("error", "Failed to update post. Please try again.");
                request.getRequestDispatcher("updatePost.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Post ID.");
            request.getRequestDispatcher("updatePost.jsp").forward(request, response);
        }
    }
}
