package com.blog.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.dao.PostDao;
import com.blog.dao.PostDaoImpl;

public class DeletePostServlet extends HttpServlet {
    private final PostDao postDao = new PostDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int postId = Integer.parseInt(request.getParameter("postId"));
            boolean success = postDao.deletePost(postId);
            
            if (success) {
                response.sendRedirect(request.getContextPath() + "/adminDashboard?message=Post deleted successfully");
            } else {
                request.setAttribute("error", "Failed to delete post. Please try again.");
                request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Post ID.");
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
        }
    }
}
