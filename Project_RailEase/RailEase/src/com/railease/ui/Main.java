package com.railease.ui;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        showWelcomeScreen();
    }
    
    public static void showWelcomeScreen() {
        while (true) {
            clearScreen();
            printHeader("WELCOME TO RAILEASE - KERALA RAILWAYS");
            System.out.println("🚆 Your Journey Begins Here! 🚆");
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║           MAIN MENU                  ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║ 1. 👤 User Login                     ║");
            System.out.println("║ 2. 📝 User Registration              ║");
            System.out.println("║ 3. 🔐 Admin Login                    ║");
            System.out.println("║ 4. 🔍 Check PNR Status               ║");
            System.out.println("║ 5. 🚂 View All Trains                ║");
            System.out.println("║ 0. ❌ Exit                           ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    // UserUI.userLogin(); // Comment out for now
                    System.out.println("User Login - Feature coming soon!");
                    pressAnyKey();
                    break;
                case 2:
                    // UserUI.userRegistration(); // Comment out for now
                    System.out.println("User Registration - Feature coming soon!");
                    pressAnyKey();
                    break;
                case 3:
                    // AdminUI.adminLogin(); // Comment out for now
                    System.out.println("Admin Login - Feature coming soon!");
                    pressAnyKey();
                    break;
                case 4:
                    checkPNRStatus();
                    break;
                case 5:
                    viewAllTrains();
                    break;
                case 0:
                    System.out.println("🙏 Thank you for using Railease! Safe travels! 🚆");
                    return;
                default:
                    System.out.println("❌ Invalid option! Please try again.");
                    pressAnyKey();
            }
        }
    }
    
    private static void checkPNRStatus() {
        clearScreen();
        printHeader("CHECK PNR STATUS");
        System.out.print("Enter PNR Number: ");
        String pnr = scanner.nextLine();
        
        // Simulate PNR check
        System.out.println("\n❌ No booking found with PNR: " + pnr);
        System.out.println("This feature will be available once booking system is implemented.");
        pressAnyKey();
    }
    
    private static void viewAllTrains() {
        clearScreen();
        printHeader("ALL AVAILABLE TRAINS");
        
        // Sample train data
        System.out.println("┌─ 12625 ───────────────────────────────────┐");
        System.out.println("│ 🚂 YERCAUD EXPRESS                       │");
        System.out.println("│ 📍 TRIVANDRUM CENTRAL → CHENNAI EGMORE   │");
        System.out.println("│ ⏰ 07:15 - 16:30 💺 120 💰 ₹ 450.00      │");
        System.out.println("└───────────────────────────────────────────┘");
        
        System.out.println("┌─ 16345 ───────────────────────────────────┐");
        System.out.println("│ 🚂 VANDE BHARAT EXPRESS                  │");
        System.out.println("│ 📍 TRIVANDRUM CENTRAL → ERNAKULAM JN     │");
        System.out.println("│ ⏰ 05:30 - 10:45 💺  80 💰 ₹ 650.00      │");
        System.out.println("└───────────────────────────────────────────┘");
        
        System.out.println("┌─ 16603 ───────────────────────────────────┐");
        System.out.println("│ 🚂 PARASURAM EXPRESS                     │");
        System.out.println("│ 📍 TRIVANDRUM CENTRAL → MANGALORE CENTRAL│");
        System.out.println("│ ⏰ 13:20 - 06:15 💺 200 💰 ₹ 350.00      │");
        System.out.println("└───────────────────────────────────────────┘");
        
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
}