package com.railease.model;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String email;
    private String phone;
    private String password;
    
    public User(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
    
    // Getters and setters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getPassword() { return password; }
    
    public void setName(String name) { this.name = name; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setPassword(String password) { this.password = password; }
    
    @Override
    public String toString() {
        return name + "|" + email + "|" + phone + "|" + password;
    }
}