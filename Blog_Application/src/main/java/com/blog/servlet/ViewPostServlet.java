package com.blog.servlet;

import com.blog.dao.PostDao;
import com.blog.dao.PostDaoImpl;
import com.blog.model.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ViewPostServlet extends HttpServlet {

    private final PostDao postDao = new PostDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get post id from request parameter
            int postId = Integer.parseInt(request.getParameter("postId"));
            
            // Retrieve post details from DAO
            Post post = postDao.getPostById(postId);

            if (post == null) {
                // If post not found, redirect to blog list
                response.sendRedirect(request.getContextPath() + "/blogList");
            } else {
                // Forward post object to viewPost.jsp for rendering
                request.setAttribute("post", post);
                request.getRequestDispatcher("viewPost.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            // Handle case where postId parameter is not a valid number
            response.sendRedirect(request.getContextPath() + "/blogList");
        }
    }
}
