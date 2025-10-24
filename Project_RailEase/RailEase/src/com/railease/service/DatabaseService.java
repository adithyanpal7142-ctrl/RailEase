package com.railease.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseService {
    private static final String DB_URL = "jdbc:sqlite:railease.db";
    
    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            
            // Create users table
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL, " +
                "email TEXT UNIQUE NOT NULL, " +
                "phone TEXT NOT NULL, " +
                "password TEXT NOT NULL" +
                ")";
            
            // Create trains table
            String createTrainsTable = "CREATE TABLE IF NOT EXISTS trains (" +
                "train_number TEXT PRIMARY KEY, " +
                "train_name TEXT NOT NULL, " +
                "source TEXT NOT NULL, " +
                "destination TEXT NOT NULL, " +
                "departure_time TEXT NOT NULL, " +
                "arrival_time TEXT NOT NULL, " +
                "total_seats INTEGER NOT NULL, " +
                "available_seats INTEGER NOT NULL, " +
                "fare REAL NOT NULL" +
                ")";
            
            // Create bookings table
            String createBookingsTable = "CREATE TABLE IF NOT EXISTS bookings (" +
                "pnr TEXT PRIMARY KEY, " +
                "user_email TEXT NOT NULL, " +
                "train_number TEXT NOT NULL, " +
                "source TEXT NOT NULL, " +
                "destination TEXT NOT NULL, " +
                "travel_date TEXT NOT NULL, " +
                "seats INTEGER NOT NULL, " +
                "passenger_names TEXT NOT NULL, " +
                "total_fare REAL NOT NULL, " +
                "status TEXT NOT NULL, " +
                "FOREIGN KEY (user_email) REFERENCES users (email), " +
                "FOREIGN KEY (train_number) REFERENCES trains (train_number)" +
                ")";
            
            stmt.execute(createUsersTable);
            stmt.execute(createTrainsTable);
            stmt.execute(createBookingsTable);
            
            // Insert sample trains
            insertSampleTrains(conn);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void insertSampleTrains(Connection conn) throws SQLException {
        String checkTrains = "SELECT COUNT(*) FROM trains";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(checkTrains)) {
            
            if (rs.getInt(1) == 0) {
                // Insert sample Kerala trains
                String[] trainInserts = {
                    "INSERT INTO trains VALUES ('12625', 'YERCAUD EXPRESS', 'TRIVANDRUM CENTRAL', 'CHENNAI EGMORE', '07:15', '16:30', 120, 120, 450.00)",
                    "INSERT INTO trains VALUES ('16345', 'VANDE BHARAT EXPRESS', 'TRIVANDRUM CENTRAL', 'ERNAKULAM JUNCTION', '05:30', '10:45', 80, 80, 650.00)",
                    "INSERT INTO trains VALUES ('16603', 'PARASURAM EXPRESS', 'TRIVANDRUM CENTRAL', 'MANGALORE CENTRAL', '13:20', '06:15', 200, 200, 350.00)",
                    "INSERT INTO trains VALUES ('12626', 'KERALA EXPRESS', 'KOCHUVELI', 'BANGALORE', '08:00', '18:30', 150, 150, 550.00)",
                    "INSERT INTO trains VALUES ('16304', 'MALABAR EXPRESS', 'KOZHIKODE', 'MUMBAI', '22:15', '20:45', 180, 180, 750.00)"
                };
                
                for (String insert : trainInserts) {
                    stmt.execute(insert);
                }
            }
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}