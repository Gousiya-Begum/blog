package com.blog.dao;

import com.blog.model.User;

public interface UserDao {
    User getUserByEmail(String email);
    boolean addUser(User user);
}
