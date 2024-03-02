package main.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import static main.app.utils.Constants.SCREEN_HEIGHT;
import static main.app.utils.Constants.SCREEN_WIDTH;

public class LoginFrame extends JFrame {
    private JButton loginButton;
    private JButton signUpButton;
    private JLabel usernameLabel;
    private JLabel welcomeLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton forgotPasswordButton;


    public LoginFrame() {
        initializeComponents();
        setLayoutComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(700, 500);
    }

    private void initializeComponents() {
        setTitle("Diet Management Tool");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        welcomeLabel = new JLabel("WELCOME", JLabel.CENTER);
        usernameLabel = new JLabel("Username:", JLabel.CENTER);
        passwordLabel = new JLabel("Password:", JLabel.CENTER);
        loginButton = new JButton("Log in");
        signUpButton = new JButton("Sign Up");
        forgotPasswordButton = new JButton("Forgot Password?");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forgotPassword();
            }
        });

        initializeSignUpButtonAction();
        getRootPane().setDefaultButton(loginButton);
        applyStyles();
    }

    private void applyStyles() {
        Font labelFont = new Font("Arial", Font.BOLD, 18);
        Font fieldFont = new Font("Arial", Font.PLAIN, 13);
        Font welcomeFont = new Font("Arial", Font.BOLD, 28);

        // Style the signup button
        signUpButton.setBackground(Color.GRAY);
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setOpaque(true);
        signUpButton.setBorderPainted(false);
        signUpButton.setFocusPainted(false);

        // Style the forget password button
        forgotPasswordButton.setBackground(Color.GRAY);
        forgotPasswordButton.setForeground(Color.WHITE);
        forgotPasswordButton.setOpaque(true);
        forgotPasswordButton.setBorderPainted(false);
        forgotPasswordButton.setFocusPainted(false);

        // Style the login button
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);

        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        usernameField.setFont(fieldFont);
        passwordField.setFont(fieldFont);
        welcomeLabel.setFont(welcomeFont);

        loginButton.setFont(labelFont);
        forgotPasswordButton.setFont(labelFont);
        signUpButton.setFont(labelFont);

    }

    private void setLayoutComponents() {
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setResizable(false);
        // Assuming SCREEN_HEIGHT and SCREEN_WIDTH are defined and represent the size of the screen
        int topPanelHeight = (int) (SCREEN_HEIGHT * 0.10); // Calculate 10% of the screen height for the top panel

        // Set the main layout to BorderLayout
        getContentPane().setLayout(new BorderLayout());

        // Create the top panel with BorderLayout for the logo
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, topPanelHeight)); // Set the preferred size for the top panel

        // Add a logo to the top panel
        JLabel logoLabel = new JLabel(); // Create a label to hold the logo
        logoLabel.setHorizontalAlignment(JLabel.CENTER); // Set the logo to align center
        URL logoUrl = getClass().getResource("/main/resources/Images/Image_5.png");
        ImageIcon logoIcon = new ImageIcon(logoUrl);
        Image logoImage = logoIcon.getImage();
        // Scale the Logo to fit the application window or a specific size
        Image scaledLogo = logoImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(scaledLogo);
        logoLabel.setIcon(scaledLogoIcon); // Add the logo to the label
        topPanel.add(logoLabel, BorderLayout.CENTER); // Add the label to the top panel

        JPanel rightPanel = new JPanel(new GridBagLayout()); // This panel will have the GridBagLayout
        JPanel leftPanel = new JPanel(new BorderLayout()); // This panel will just contain an image

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH; // Change to NORTH if you want components to be aligned to the top
        gbc.insets = new Insets(10, 0, 10, 0);

        // Add all the components to the right panel
        rightPanel.add(welcomeLabel, gbc);
        gbc.gridy++;
        rightPanel.add(usernameLabel, gbc);
        gbc.gridy++;
        rightPanel.add(usernameField, gbc);
        gbc.gridy++;
        rightPanel.add(passwordLabel, gbc);
        gbc.gridy++;
        rightPanel.add(passwordField, gbc);
        gbc.gridy++;
        rightPanel.add(loginButton, gbc);
        gbc.gridy++;
        rightPanel.add(forgotPasswordButton, gbc); // Add the forgotPasswordButton here
        gbc.gridy++;
        rightPanel.add(signUpButton, gbc);
        gbc.gridy++;

        try {
            URL imageUrl = getClass().getResource("/main/resources/Images/Image_4.png"); //file path
            if (imageUrl != null) {
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                Image originalImage = originalIcon.getImage();

                // Scale the image to fit the application window or a specific size
                Image scaledImage = originalImage.getScaledInstance(450, 400, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                JLabel imageLabel = new JLabel(scaledIcon);
                imageLabel.setHorizontalAlignment(JLabel.CENTER);
                leftPanel.add(imageLabel, BorderLayout.CENTER);
                leftPanel.setOpaque(false);
            } else {
                System.err.println("Image file not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading the image.");
        }


        // Add the panels to the frame
        getContentPane().add(topPanel, BorderLayout.NORTH); // Top panel at the top
        getContentPane().add(rightPanel, BorderLayout.EAST); // Add the left panel to the left side
        getContentPane().add(leftPanel, BorderLayout.CENTER); // Add the right panel to the center (which will effectively be the right side)

        setLocationRelativeTo(null);
        setResizable(true);
        pack();

    }

    private void initializeSignUpButtonAction() {
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignUpFrame signUpFrame = new SignUpFrame();
                signUpFrame.setVisible(true);
                dispose(); // Close the LoginFrame when the SignUpFrame is opened
            }
        });
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (verifyUser(username, password) == 1) { // Username and password match
            JOptionPane.showMessageDialog(this, "Login successful.", "Welcome", JOptionPane.INFORMATION_MESSAGE);

            // Close the LoginFrame
            dispose();

            // Open the MenuFrame as the main application window
            MenuFrame menuFrame = new MenuFrame(username); // Assuming the MenuFrame has a default constructor
            menuFrame.setVisible(true);

        } else if (verifyUser(username, password) == 0) { // User file does not exist
            JOptionPane.showMessageDialog(this, "No account found. Please sign up.", "Account Not Found", JOptionPane.ERROR_MESSAGE);
        } else { // User exists but password does not match
            JOptionPane.showMessageDialog(this, "Incorrect username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int verifyUser(String username, String password) {
        File usersDir = new File("users"); // Updated path
        File userFile = new File(usersDir, username + ".csv");

        if (!userFile.exists()) {
            return 0; // User file does not exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    String storedUsername = parts[0].trim();
                    String storedPassword = parts[1].trim();
                    if (username.equals(storedUsername) && password.equals(storedPassword)) {
                        return 1; // Username and password match
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1; // User exists but password does not match
    }

    private void forgotPassword() {
        String fullName = JOptionPane.showInputDialog(this, "Enter your full name:");
        if (fullName != null && !fullName.trim().isEmpty()) {
            String[] credentials = retrievePasswordIfFullNameMatches(fullName.trim());
            if (credentials != null) {
                // Display both the username and password
                JOptionPane.showMessageDialog(this, "Your username is: " + credentials[0] + "\nYour password is: " + credentials[1], "Credentials Retrieved", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Full name was incorrect. Verification failed.", "Verification Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String[] retrievePasswordIfFullNameMatches(String fullName) {
        File usersDir = new File("users");
        File[] userFiles = usersDir.listFiles();
        if (userFiles != null) {
            for (File userFile : userFiles) {
                try (BufferedReader reader = new BufferedReader(new FileReader(userFile))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length >= 3) {
                            String storedFullName = parts[2].trim();
                            if (fullName.equals(storedFullName)) {
                                // Return both the username and password
                                return new String[]{parts[0].trim(), parts[1].trim()};
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
