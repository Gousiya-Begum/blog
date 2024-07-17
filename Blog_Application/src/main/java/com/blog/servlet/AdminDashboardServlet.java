package com.blog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.blog.dao.PostDao;
import com.blog.dao.PostDaoImpl;
import com.blog.model.Post;
import com.blog.model.User;

@WebServlet("/adminDashboard")
public class AdminDashboardServlet extends HttpServlet {

    private final PostDao postDao = new PostDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null || !"Admin".equals(user.getRole())) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        List<Post> posts = postDao.getAllPosts();
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("/adminDashboard.jsp").forward(request, response);
    }
}
