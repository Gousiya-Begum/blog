package com.blog.dao;

import java.util.List;

import com.blog.model.Post;

public interface PostDao {
    List<Post> getAllPosts();

    Post getPostById(int id);

    boolean addPost(Post post);

    boolean updatePost(Post post);

    boolean deletePost(int id);
}
