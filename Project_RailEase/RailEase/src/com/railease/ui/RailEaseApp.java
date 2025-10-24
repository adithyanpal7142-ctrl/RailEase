package com.railease.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class RailEaseApp extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Map<String, String> pnrDatabase = new HashMap<>();
    private Map<String, String> userDatabase = new HashMap<>();
    
    // Colors
    private final Color PRIMARY_COLOR = new Color(0, 102, 204);
    private final Color SECONDARY_COLOR = new Color(240, 240, 240);
    
    public RailEaseApp() {
        setupGUI();
        // Initialize some test users
        userDatabase.put("user@test.com", "password");
        userDatabase.put("test@user.com", "123456");
    }
    
    private void setupGUI() {
        setTitle("🚆 Railease - Kerala Railways");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 650);
        setLocationRelativeTo(null);
        
        // Create card layout for navigation
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Create different screens
        mainPanel.add(createWelcomeScreen(), "WELCOME");
        mainPanel.add(createUserLoginScreen(), "USER_LOGIN");
        mainPanel.add(createUserRegisterScreen(), "USER_REGISTER");
        mainPanel.add(createAdminLoginScreen(), "ADMIN_LOGIN");
        mainPanel.add(createPNRStatusScreen(), "PNR_STATUS");
        mainPanel.add(createTrainSearchScreen(), "TRAIN_SEARCH");
        mainPanel.add(createAdminDashboard(), "ADMIN_DASHBOARD");
        
        add(mainPanel);
        showScreen("WELCOME");
    }
    
    private JPanel createWelcomeScreen() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setBorder(new EmptyBorder(30, 0, 30, 0));
        
        JLabel titleLabel = new JLabel("🚆 WELCOME TO RAILEASE", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel);
        
        // Center content
        JPanel centerPanel = new JPanel(new GridLayout(6, 1, 10, 10));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(new EmptyBorder(50, 100, 50, 100));
        
        JButton userLoginBtn = createStyledButton("👤 User Login", PRIMARY_COLOR);
        JButton userRegisterBtn = createStyledButton("📝 User Registration", new Color(76, 175, 80));
        JButton adminLoginBtn = createStyledButton("🔐 Admin Login", new Color(156, 39, 176));
        JButton pnrStatusBtn = createStyledButton("🔍 Check PNR Status", new Color(255, 87, 34));
        JButton viewTrainsBtn = createStyledButton("🚂 View All Trains", new Color(0, 150, 136));
        JButton exitBtn = createStyledButton("❌ Exit", new Color(244, 67, 54));
        
        userLoginBtn.addActionListener(e -> showScreen("USER_LOGIN"));
        userRegisterBtn.addActionListener(e -> showScreen("USER_REGISTER"));
        adminLoginBtn.addActionListener(e -> showScreen("ADMIN_LOGIN"));
        pnrStatusBtn.addActionListener(e -> showScreen("PNR_STATUS"));
        viewTrainsBtn.addActionListener(e -> showScreen("TRAIN_SEARCH"));
        exitBtn.addActionListener(e -> System.exit(0));
        
        centerPanel.add(userLoginBtn);
        centerPanel.add(userRegisterBtn);
        centerPanel.add(adminLoginBtn);
        centerPanel.add(pnrStatusBtn);
        centerPanel.add(viewTrainsBtn);
        centerPanel.add(exitBtn);
        
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(centerPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createUserLoginScreen() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Header with back button
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        JButton backBtn = createSmallButton("← Back");
        backBtn.addActionListener(e -> showScreen("WELCOME"));
        
        JLabel titleLabel = new JLabel("👤 User Login", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        headerPanel.add(backBtn, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Login form
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 15, 15));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(80, 150, 80, 150));
        
        JLabel emailLabel = new JLabel("📧 Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField emailField = new JTextField();
        
        JLabel passwordLabel = new JLabel("🔒 Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JPasswordField passwordField = new JPasswordField();
        
        JButton loginBtn = createStyledButton("Login", PRIMARY_COLOR);
        JButton registerBtn = createStyledButton("New User? Register Here", new Color(76, 175, 80));
        
        loginBtn.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            
            if (userDatabase.containsKey(email) && userDatabase.get(email).equals(password)) {
                JOptionPane.showMessageDialog(this, "Login successful! Welcome back! ✅");
                showScreen("TRAIN_SEARCH");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email or password! ❌", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        registerBtn.addActionListener(e -> showScreen("USER_REGISTER"));
        
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(loginBtn);
        formPanel.add(registerBtn);
        
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createUserRegisterScreen() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(76, 175, 80));
        headerPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        JButton backBtn = createSmallButton("← Back");
        backBtn.addActionListener(e -> showScreen("WELCOME"));
        
        JLabel titleLabel = new JLabel("📝 User Registration", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        headerPanel.add(backBtn, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Registration form
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(30, 100, 30, 100));
        
        JLabel nameLabel = new JLabel("👤 Full Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField nameField = new JTextField();
        
        JLabel emailLabel = new JLabel("📧 Email:");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField emailField = new JTextField();
        
        JLabel phoneLabel = new JLabel("📞 Phone:");
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField phoneField = new JTextField();
        
        JLabel passwordLabel = new JLabel("🔒 Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JPasswordField passwordField = new JPasswordField();
        
        JLabel confirmLabel = new JLabel("🔒 Confirm Password:");
        confirmLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JPasswordField confirmField = new JPasswordField();
        
        JButton registerBtn = createStyledButton("Register", new Color(76, 175, 80));
        
        registerBtn.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String password = new String(passwordField.getPassword());
            String confirm = new String(confirmField.getPassword());
            
            if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (!password.equals(confirm)) {
                JOptionPane.showMessageDialog(this, "Passwords don't match!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Add user to database
                userDatabase.put(email, password);
                JOptionPane.showMessageDialog(this, 
                    "Registration successful! ✅\n\nName: " + name + "\nEmail: " + email + "\nPhone: " + phone,
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                showScreen("WELCOME");
            }
        });
        
        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(confirmLabel);
        formPanel.add(confirmField);
        formPanel.add(new JLabel());
        formPanel.add(registerBtn);
        
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    // ADD THIS MISSING METHOD
    private JPanel createAdminLoginScreen() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(156, 39, 176));
        headerPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        JButton backBtn = createSmallButton("← Back");
        backBtn.addActionListener(e -> showScreen("WELCOME"));
        
        JLabel titleLabel = new JLabel("🔐 Admin Login", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        headerPanel.add(backBtn, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Admin login form
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 15, 15));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(100, 150, 100, 150));
        
        JLabel userLabel = new JLabel("👤 Admin ID:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField userField = new JTextField();
        
        JLabel passLabel = new JLabel("🔒 Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JPasswordField passField = new JPasswordField();
        
        JButton loginBtn = createStyledButton("Admin Login", new Color(156, 39, 176));
        
        loginBtn.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
            
            if (username.equals("admin") && password.equals("admin123")) {
                JOptionPane.showMessageDialog(this, "Admin login successful! ✅", "Success", JOptionPane.INFORMATION_MESSAGE);
                showScreen("ADMIN_DASHBOARD");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid admin credentials! ❌", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        formPanel.add(userLabel);
        formPanel.add(userField);
        formPanel.add(passLabel);
        formPanel.add(passField);
        formPanel.add(loginBtn);
        
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createAdminDashboard() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(156, 39, 176));
        headerPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        JButton backBtn = createSmallButton("← Logout");
        backBtn.addActionListener(e -> showScreen("WELCOME"));
        
        JLabel titleLabel = new JLabel("🔐 Admin Dashboard", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        headerPanel.add(backBtn, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Admin features
        JPanel featuresPanel = new JPanel(new GridLayout(4, 1, 15, 15));
        featuresPanel.setBackground(Color.WHITE);
        featuresPanel.setBorder(new EmptyBorder(50, 100, 50, 100));
        
        JButton manageTrainsBtn = createStyledButton("🚂 Manage Trains", new Color(156, 39, 176));
        JButton viewBookingsBtn = createStyledButton("📊 View All Bookings", new Color(76, 175, 80));
        JButton manageUsersBtn = createStyledButton("👥 Manage Users", new Color(255, 87, 34));
        JButton reportsBtn = createStyledButton("📈 Generate Reports", new Color(0, 150, 136));
        
        manageTrainsBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, 
            "Train Management Features:\n\n• Add New Trains\n• Update Train Schedules\n• Delete Trains\n• Manage Seats & Fares", 
            "Manage Trains", JOptionPane.INFORMATION_MESSAGE));
        
        viewBookingsBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, 
            "All Bookings View:\n\n• View All Reservations\n• Cancel Bookings\n• Update Booking Status\n• Export Booking Data", 
            "View Bookings", JOptionPane.INFORMATION_MESSAGE));
        
        manageUsersBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, 
            "User Management:\n\n• View All Users\n• Delete Users\n• Reset Passwords\n• User Analytics", 
            "Manage Users", JOptionPane.INFORMATION_MESSAGE));
        
        reportsBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, 
            "Reports:\n\n• Daily Revenue Reports\n• Booking Statistics\n• Train Performance\n• User Activity", 
            "Generate Reports", JOptionPane.INFORMATION_MESSAGE));
        
        featuresPanel.add(manageTrainsBtn);
        featuresPanel.add(viewBookingsBtn);
        featuresPanel.add(manageUsersBtn);
        featuresPanel.add(reportsBtn);
        
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(featuresPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createPNRStatusScreen() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(255, 87, 34));
        headerPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        JButton backBtn = createSmallButton("← Back");
        backBtn.addActionListener(e -> showScreen("WELCOME"));
        
        JLabel titleLabel = new JLabel("🔍 Check PNR Status", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        headerPanel.add(backBtn, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // PNR Check form
        JPanel formPanel = new JPanel(new GridLayout(3, 1, 15, 15));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(100, 150, 100, 150));
        
        JLabel pnrLabel = new JLabel("🔢 Enter PNR Number:");
        pnrLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JTextField pnrField = new JTextField();
        pnrField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JButton checkBtn = createStyledButton("Check Status", new Color(255, 87, 34));
        
        checkBtn.addActionListener(e -> {
            String pnr = pnrField.getText().trim();
            if (pnr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a PNR number!", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (pnrDatabase.containsKey(pnr)) {
                JOptionPane.showMessageDialog(this, 
                    "✅ PNR Status Found!\n\nPNR: " + pnr + "\n" + pnrDatabase.get(pnr),
                    "PNR Status", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else if (pnr.equals("PNR123456")) {
                JOptionPane.showMessageDialog(this, 
                    "✅ PNR Status Found!\n\n" +
                    "PNR: PNR123456\n" +
                    "Train: VANDE BHARAT EXPRESS\n" +
                    "From: TRIVANDRUM CENTRAL\n" +
                    "To: ERNAKULAM JUNCTION\n" +
                    "Date: 15-12-2024\n" +
                    "Status: CONFIRMED\n" +
                    "Seats: 2\n" +
                    "Fare: ₹1300.00",
                    "PNR Status", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "❌ PNR Not Found!\n\nPNR: " + pnr + "\n\nPlease check the PNR number and try again.",
                    "PNR Status", 
                    JOptionPane.WARNING_MESSAGE);
            }
        });
        
        formPanel.add(pnrLabel);
        formPanel.add(pnrField);
        formPanel.add(checkBtn);
        
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createTrainSearchScreen() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(0, 150, 136));
        headerPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        JButton backBtn = createSmallButton("← Back");
        backBtn.addActionListener(e -> showScreen("WELCOME"));
        
        JLabel titleLabel = new JLabel("🚂 Available Trains - Kerala Routes", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        
        headerPanel.add(backBtn, BorderLayout.WEST);
        headerPanel.add(titleLabel, BorderLayout.CENTER);
        
        // Train list in scroll pane
        JPanel trainPanel = new JPanel();
        trainPanel.setLayout(new BoxLayout(trainPanel, BoxLayout.Y_AXIS));
        trainPanel.setBackground(Color.WHITE);
        trainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        String[][] trains = {
            {"12625", "YERCAUD EXPRESS", "TRIVANDRUM CENTRAL", "CHENNAI EGMORE", "07:15", "16:30", "120", "₹450.00"},
            {"16345", "VANDE BHARAT EXPRESS", "TRIVANDRUM CENTRAL", "ERNAKULAM JN", "05:30", "10:45", "80", "₹650.00"},
            {"16603", "PARASURAM EXPRESS", "TRIVANDRUM CENTRAL", "MANGALORE CENTRAL", "13:20", "06:15", "200", "₹350.00"},
            {"12626", "KERALA EXPRESS", "KOCHUVELI", "BANGALORE", "08:00", "18:30", "150", "₹550.00"},
            {"16304", "MALABAR EXPRESS", "KOZHIKODE", "MUMBAI", "22:15", "20:45", "180", "₹750.00"}
        };
        
        for (String[] train : trains) {
            trainPanel.add(createTrainCard(train[0], train[1], train[2], train[3], train[4], train[5], train[6], train[7]));
            trainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }
        
        JScrollPane scrollPane = new JScrollPane(trainPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        
        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel createTrainCard(String number, String name, String from, String to, String dep, String arr, String seats, String fare) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(SECONDARY_COLOR);
        card.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(Color.LIGHT_GRAY, 1),
            new EmptyBorder(15, 15, 15, 15)
        ));
        card.setMaximumSize(new Dimension(800, 120));
        
        JLabel trainHeader = new JLabel("🚂 " + name + " (" + number + ")");
        trainHeader.setFont(new Font("Arial", Font.BOLD, 16));
        trainHeader.setForeground(PRIMARY_COLOR);
        
        JPanel detailsPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        detailsPanel.setBackground(SECONDARY_COLOR);
        
        detailsPanel.add(createDetailLabel("📍 " + from + " → " + to));
        detailsPanel.add(createDetailLabel("⏰ " + dep + " - " + arr));
        detailsPanel.add(createDetailLabel("💺 Seats Available: " + seats));
        detailsPanel.add(createDetailLabel("💰 Fare: " + fare));
        
        JButton bookBtn = createSmallButton("Book Now");
        bookBtn.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(this, 
                "Book " + name + "?\n\nFrom: " + from + "\nTo: " + to + "\nFare: " + fare,
                "Confirm Booking",
                JOptionPane.YES_NO_OPTION);
                
            if (result == JOptionPane.YES_OPTION) {
                String pnrNumber = "PNR" + (100000 + (int)(Math.random() * 900000));
                
                pnrDatabase.put(pnrNumber, 
                    "Train: " + name + "\n" +
                    "From: " + from + "\n" +
                    "To: " + to + "\n" +
                    "Fare: " + fare + "\n" +
                    "Status: CONFIRMED");
                
                JOptionPane.showMessageDialog(this, 
                    "Booking Confirmed! ✅\n\n" +
                    "Train: " + name + "\n" +
                    "PNR: " + pnrNumber + "\n" +
                    "From: " + from + "\n" +
                    "To: " + to + "\n" +
                    "Fare: " + fare + "\n\n" +
                    "Thank you for choosing Railease! 🚆",
                    "Booking Successful",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        card.add(trainHeader, BorderLayout.NORTH);
        card.add(detailsPanel, BorderLayout.CENTER);
        card.add(bookBtn, BorderLayout.EAST);
        
        return card;
    }
    
    private JLabel createDetailLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        return label;
    }
    
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(12, 25, 12, 25));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(bgColor.darker());
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor);
            }
        });
        
        return button;
    }
    
    private JButton createSmallButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 12));
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return button;
    }
    
    private void showScreen(String screenName) {
        cardLayout.show(mainPanel, screenName);
    }
    
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        new RailEaseApp().setVisible(true);
    });
}
}