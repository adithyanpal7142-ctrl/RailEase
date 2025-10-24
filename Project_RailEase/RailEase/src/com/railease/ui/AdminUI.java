package com.railease.ui;

import java.util.Scanner;

public class AdminUI {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void adminLogin() {
        clearScreen();
        printHeader("ADMIN LOGIN");
        
        System.out.print("👤 Enter Admin ID: ");
        String adminId = scanner.nextLine();
        System.out.print("🔒 Enter Password: ");
        String password = scanner.nextLine();
        
        // Simple admin validation
        if (adminId.equals("admin") && password.equals("admin123")) {
            showSuccess("Admin login successful! 👋");
            pressAnyKey();
            showAdminDashboard();
        } else {
            showError("Invalid admin credentials!");
            pressAnyKey();
        }
    }
    
    private static void showAdminDashboard() {
        while (true) {
            clearScreen();
            printHeader("ADMIN DASHBOARD");
            
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║           ADMIN MENU                 ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║ 1. 🚂 Add New Train                 ║");
            System.out.println("║ 2. 📋 View All Trains               ║");
            System.out.println("║ 3. ✏️ Update Train                  ║");
            System.out.println("║ 4. 🗑️ Remove Train                  ║");
            System.out.println("║ 0. 🚪 Logout                        ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    addNewTrain();
                    break;
                case 2:
                    viewAllTrains();
                    break;
                case 3:
                    updateTrain();
                    break;
                case 4:
                    removeTrain();
                    break;
                case 0:
                    logout();
                    return;
                default:
                    showError("Invalid option!");
                    pressAnyKey();
            }
        }
    }
    
    private static void addNewTrain() {
        clearScreen();
        printHeader("ADD NEW TRAIN");
        
        System.out.print("Enter Train Number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter Train Name: ");
        String trainName = scanner.nextLine();
        System.out.print("Enter Source Station: ");
        String source = scanner.nextLine();
        System.out.print("Enter Destination Station: ");
        String destination = scanner.nextLine();
        System.out.print("Enter Departure Time (HH:MM): ");
        String departureTime = scanner.nextLine();
        System.out.print("Enter Arrival Time (HH:MM): ");
        String arrivalTime = scanner.nextLine();
        System.out.print("Enter Total Seats: ");
        int totalSeats = scanner.nextInt();
        System.out.print("Enter Fare per Seat: ");
        double fare = scanner.nextDouble();
        scanner.nextLine();
        
        showSuccess("Train added successfully! ✅");
        pressAnyKey();
    }
    
    private static void viewAllTrains() {
        clearScreen();
        printHeader("ALL TRAINS");
        
        System.out.println("┌─ 12625 ───────────────────────────────────┐");
        System.out.println("│ 🚂 YERCAUD EXPRESS                       │");
        System.out.println("│ 📍 TRIVANDRUM → CHENNAI                 │");
        System.out.println("│ ⏰ 07:15 - 16:30 💺 120 💰 ₹450         │");
        System.out.println("└───────────────────────────────────────────┘");
        
        pressAnyKey();
    }
    
    private static void updateTrain() {
        clearScreen();
        printHeader("UPDATE TRAIN");
        showInfo("Update train functionality to be implemented");
        pressAnyKey();
    }
    
    private static void removeTrain() {
        clearScreen();
        printHeader("REMOVE TRAIN");
        showInfo("Remove train functionality to be implemented");
        pressAnyKey();
    }
    
    private static void logout() {
        showSuccess("Admin logged out successfully! 👋");
        pressAnyKey();
    }
    
    // Helper methods
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("\n".repeat(50));
        }
    }
    
    public static void printHeader(String title) {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║ 🚆 " + centerText(title, 52) + " 🚆 ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println();
    }
    
    private static String centerText(String text, int width) {
        if (text.length() >= width) {
            return text.substring(0, width);
        }
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - text.length() - padding);
    }
    
    public static void pressAnyKey() {
        System.out.println("\n┌──────────────────────────────────────────────────────────┐");
        System.out.println("│ 👆 Press Enter to continue...                            │");
        System.out.println("└──────────────────────────────────────────────────────────┘");
        scanner.nextLine();
    }
    
    public static void showSuccess(String message) {
        System.out.println("✅ " + message);
    }
    
    public static void showError(String message) {
        System.out.println("❌ " + message);
    }
    
    public static void showInfo(String message) {
        System.out.println("ℹ️ " + message);
    }
}