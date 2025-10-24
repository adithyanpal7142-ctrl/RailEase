package com.railease.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileStorageService {
    private static final String DATA_DIR = "data";
    
    // Create data directory if it doesn't exist
    static {
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }
    
    // Simple text-based user storage (no model dependencies)
    public static boolean saveUser(String email, String password, String name, String phone) {
        try {
            File file = new File(DATA_DIR + "/users.txt");
            FileWriter fw = new FileWriter(file, true);
            fw.write(email + "," + password + "," + name + "," + phone + "\n");
            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean validateUser(String email, String password) {
        try {
            File file = new File(DATA_DIR + "/users.txt");
            if (!file.exists()) return false;
            
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2 && parts[0].equals(email) && parts[1].equals(password)) {
                    br.close();
                    return true;
                }
            }
            br.close();
            return false;
        } catch (IOException e) {
            System.out.println("Error validating user: " + e.getMessage());
            return false;
        }
    }
    
    public static boolean userExists(String email) {
        try {
            File file = new File(DATA_DIR + "/users.txt");
            if (!file.exists()) return false;
            
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 1 && parts[0].equals(email)) {
                    br.close();
                    return true;
                }
            }
            br.close();
            return false;
        } catch (IOException e) {
            System.out.println("Error checking user: " + e.getMessage());
            return false;
        }
    }
    
    // Simple PNR storage
    public static void savePNR(String pnr, String trainDetails) {
        try {
            File file = new File(DATA_DIR + "/pnr.txt");
            FileWriter fw = new FileWriter(file, true);
            fw.write(pnr + ":" + trainDetails + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving PNR: " + e.getMessage());
        }
    }
    
    public static String getPNRStatus(String pnr) {
        try {
            File file = new File(DATA_DIR + "/pnr.txt");
            if (!file.exists()) return null;
            
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length >= 2 && parts[0].equals(pnr)) {
                    br.close();
                    return parts[1];
                }
            }
            br.close();
            return null;
        } catch (IOException e) {
            System.out.println("Error reading PNR: " + e.getMessage());
            return null;
        }
    }
    
    // Initialize with default admin
    public static boolean validateAdmin(String username, String password) {
        return username.equals("admin") && password.equals("admin123");
    }
}