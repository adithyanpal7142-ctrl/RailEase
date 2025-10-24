package com.railease.ui;

import java.util.Scanner;

public class UserUI {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void userLogin() {
        clearScreen();
        printHeader("USER LOGIN");
        
        System.out.print("📧 Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("🔒 Enter Password: ");
        String password = scanner.nextLine();
        
        // Simple validation
        if (email.equals("user@test.com") && password.equals("password")) {
            showSuccess("Login successful! Welcome User! 👋");
            pressAnyKey();
            showUserDashboard();
        } else {
            showError("Invalid email or password!");
            pressAnyKey();
        }
    }
    
    public static void userRegistration() {
        clearScreen();
        printHeader("USER REGISTRATION");
        
        System.out.print("👤 Enter Full Name: ");
        String name = scanner.nextLine();
        System.out.print("📧 Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("📞 Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("🔒 Enter Password: ");
        String password = scanner.nextLine();
        
        showSuccess("Registration successful! You can now login. ✅");
        pressAnyKey();
    }
    
    private static void showUserDashboard() {
        while (true) {
            clearScreen();
            printHeader("USER DASHBOARD");
            
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║           USER MENU                  ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║ 1. 🚂 Search & Book Trains          ║");
            System.out.println("║ 2. 📋 View Booking History          ║");
            System.out.println("║ 3. 🔍 Check PNR Status              ║");
            System.out.println("║ 4. 👤 View Profile                  ║");
            System.out.println("║ 0. 🚪 Logout                        ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    searchAndBookTrains();
                    break;
                case 2:
                    viewBookingHistory();
                    break;
                case 3:
                    checkUserPNRStatus();
                    break;
                case 4:
                    viewProfile();
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
    
    private static void searchAndBookTrains() {
        clearScreen();
        printHeader("SEARCH & BOOK TRAINS");
        
        System.out.print("Enter Source Station: ");
        String source = scanner.nextLine();
        System.out.print("Enter Destination Station: ");
        String destination = scanner.nextLine();
        System.out.print("Enter Travel Date (DD-MM-YYYY): ");
        String travelDate = scanner.nextLine();
        
        showInfo("Searching trains from " + source + " to " + destination);
        
        // Show sample trains
        System.out.println("\n┌─ 12625 ───────────────────────────────────┐");
        System.out.println("│ 🚂 YERCAUD EXPRESS                       │");
        System.out.println("│ 📍 " + source + " → " + destination + "          │");
        System.out.println("│ ⏰ 07:15 - 16:30 💺 120 💰 ₹450         │");
        System.out.println("└───────────────────────────────────────────┘");
        
        System.out.print("\nBook this train? (yes/no): ");
        String confirm = scanner.nextLine();
        
        if (confirm.equalsIgnoreCase("yes")) {
            showSuccess("Booking confirmed! PNR: PNR123456 🎫");
        } else {
            showInfo("Booking cancelled.");
        }
        pressAnyKey();
    }
    
    private static void viewBookingHistory() {
        clearScreen();
        printHeader("BOOKING HISTORY");
        showInfo("No bookings found! 🎫");
        pressAnyKey();
    }
    
    private static void checkUserPNRStatus() {
        clearScreen();
        printHeader("CHECK PNR STATUS");
        System.out.print("Enter PNR Number: ");
        String pnr = scanner.nextLine();
        showInfo("Checking PNR: " + pnr);
        pressAnyKey();
    }
    
    private static void viewProfile() {
        clearScreen();
        printHeader("USER PROFILE");
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│ 👤 Name: User Test                                      │");
        System.out.println("│ 📧 Email: user@test.com                                │");
        System.out.println("│ 📞 Phone: 9876543210                                   │");
        System.out.println("└──────────────────────────────────────────────────────────┘");
        pressAnyKey();
    }
    
    private static void logout() {
        showSuccess("Logged out successfully! 👋");
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