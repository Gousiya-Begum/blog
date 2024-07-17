package com.blog.util;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Generate BCrypt hash from a plain text password
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(10));
    }

    // Check if the provided plain text password matches the hashed password
    public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
