package com.railease.ui;

import java.util.Scanner;

public class GUIComponents {
    public static Scanner scanner = new Scanner(System.in);
    
    // Kerala railway stations
    public static final String[] KERALA_STATIONS = {
        "TRIVANDRUM CENTRAL", "KOCHUVELI", "KOLLAM JUNCTION", "KAYANKULAM",
        "ALAPPUZHA", "ERNAKULAM JUNCTION", "ALUVA", "THRISSURE", 
        "PALAKKAD JUNCTION", "KOZHIKODE", "KANNUR", "KASARAGOD"
    };
    
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
    
    public static void printSubHeader(String title) {
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│ " + centerText(title, 54) + " │");
        System.out.println("└──────────────────────────────────────────────────────────┘");
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
    
    public static void printStationList() {
        System.out.println("┌──────────────────────────────────────────────────────────┐");
        System.out.println("│ 🗺️  KERALA STATIONS LIST                                │");
        System.out.println("├──────────────────────────────────────────────────────────┤");
        for (int i = 0; i < KERALA_STATIONS.length; i++) {
            System.out.printf("│ %d. %-47s │\n", (i + 1), KERALA_STATIONS[i]);
        }
        System.out.println("└──────────────────────────────────────────────────────────┘");
    }
}