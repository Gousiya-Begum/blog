package com.blog.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.dao.PostDao;
import com.blog.dao.PostDaoImpl;
import com.blog.model.Post;

@WebServlet("/blogList")
public class BlogListServlet extends HttpServlet {

    private final PostDao postDao = new PostDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Post> posts = postDao.getAllPosts();
        request.setAttribute("posts", posts);
        request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
    }
}
