package main.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LoginFrame extends JFrame {
    private JButton startButton;
    private JButton signUpButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        initializeComponents();
        setLayoutComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400, 300);
    }

    private void initializeComponents() {
        setTitle("Diet Management Tool");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        usernameLabel = new JLabel("Username:", JLabel.CENTER);
        passwordLabel = new JLabel("Password:", JLabel.CENTER);
        startButton = new JButton("Log in");
        signUpButton = new JButton("Sign Up");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        initializeSignUpButtonAction();
        applyStyles();
    }

    private void applyStyles() {
        Font labelFont = new Font("Arial", Font.PLAIN, 12);
        Font fieldFont = new Font("Arial", Font.PLAIN, 12);

        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        usernameField.setFont(fieldFont);
        passwordField.setFont(fieldFont);

        startButton.setFont(labelFont);
        signUpButton.setFont(labelFont);
    }

    private void setLayoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        add(usernameLabel, gbc);
        add(usernameField, gbc);
        add(passwordLabel, gbc);
        add(passwordField, gbc);
        add(startButton, gbc);
        add(signUpButton, gbc);
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
            MenuFrame menuFrame = new MenuFrame(); // Assuming the MenuFrame has a default constructor
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
