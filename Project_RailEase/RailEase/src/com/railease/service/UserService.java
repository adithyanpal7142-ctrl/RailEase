package com.railease.service;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    
    // Simple user storage for testing
    private static Map<String, String> userDatabase = new HashMap<>();
    
    static {
        // Add some test users
        userDatabase.put("user@test.com", "password");
        userDatabase.put("test@user.com", "123456");
    }
    
    public boolean loginUser(String email, String password) {
        try {
            if (userDatabase.containsKey(email) && userDatabase.get(email).equals(password)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean registerUser(String name, String email, String phone, String password) {
        try {
            // Check if email already exists
            if (userDatabase.containsKey(email)) {
                return false;
            }
            
            // Add new user
            userDatabase.put(email, password);
            System.out.println("Registered user: " + email);
            return true;
        } catch (Exception e) {
            System.out.println("Registration error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean changePassword(String email, String currentPassword, String newPassword) {
        try {
            if (userDatabase.containsKey(email) && userDatabase.get(email).equals(currentPassword)) {
                userDatabase.put(email, newPassword);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Password change error: " + e.getMessage());
            return false;
        }
    }
    
    // Method to check if email exists (for registration)
    public boolean emailExists(String email) {
        return userDatabase.containsKey(email);
    }
}