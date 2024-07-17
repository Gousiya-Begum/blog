package com.blog.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.dao.UserDao;
import com.blog.dao.UserDaoImpl;
import com.blog.model.User;
import com.blog.util.PasswordUtil;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Hash the password before storing it
        String hashedPassword = PasswordUtil.hashPassword(password);

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(hashedPassword);
        newUser.setRole("Viewer"); 

        boolean success = userDao.addUser(newUser);
        if (success) {
            response.sendRedirect(request.getContextPath() + "login.jsp");
        } else {
            request.setAttribute("error", "Failed to register. Please try again.");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}
