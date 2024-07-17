package com.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.blog.model.Post;
import com.blog.util.DBUtil;

public class PostDaoImpl implements PostDao {

    private final Connection connection;
    

    public PostDaoImpl(){
        this.connection = DBUtil.getConnection();
    }

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        String query = "SELECT * FROM posts ORDER BY createdAt DESC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String imageUrl = rs.getString("imageUrl");
                String videoUrl = rs.getString("videoUrl");
                LocalDateTime createdAt = rs.getTimestamp("createdAt").toLocalDateTime();
                LocalDateTime updatedAt = rs.getTimestamp("updatedAt").toLocalDateTime();

                Post post = new Post(id, title, content, imageUrl, videoUrl, createdAt, updatedAt);
                posts.add(post);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    @Override
    public Post getPostById(int id) {
        String query = "SELECT * FROM posts WHERE id = ?";
        Post post = null;

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                String imageUrl = rs.getString("imageUrl");
                String videoUrl = rs.getString("videoUrl");
                LocalDateTime createdAt = rs.getTimestamp("createdAt").toLocalDateTime();
                LocalDateTime updatedAt = rs.getTimestamp("updatedAt").toLocalDateTime();

                post = new Post(id, title, content, imageUrl, videoUrl, createdAt, updatedAt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return post;
    }

    @Override
    public boolean addPost(Post post) {
        String query = "INSERT INTO posts (title, content, imageUrl, videoUrl, createdAt, updatedAt) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContent());
            pstmt.setString(3, post.getImageUrl());
            pstmt.setString(4, post.getVideoUrl());
            pstmt.setTimestamp(5, Timestamp.valueOf(post.getCreatedAt()));
            pstmt.setTimestamp(6, Timestamp.valueOf(post.getUpdatedAt()));

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updatePost(Post post) {
        String query = "UPDATE posts SET title = ?, content = ?, imageUrl = ?, videoUrl = ?, createdAt=? ,updatedAt = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContent());
            pstmt.setString(3, post.getImageUrl());
            pstmt.setString(4, post.getVideoUrl());
            pstmt.setTimestamp(5, Timestamp.valueOf(post.getCreatedAt()));
            pstmt.setTimestamp(5, Timestamp.valueOf(post.getUpdatedAt()));
            pstmt.setInt(6, post.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deletePost(int id) {
        String query = "DELETE FROM posts WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
