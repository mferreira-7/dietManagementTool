package main.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SignUpFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public SignUpFrame() {
        setTitle("Sign Up");
        initializeComponents();
        setLayout(new FlowLayout());
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void initializeComponents() {
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        registerButton = new JButton("Register");

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());

                // Username and password validation
                if (!validateInput(username, password)) {
                    return;
                }

                // Check if user already exists
                if (userExists(username)) {
                    JOptionPane.showMessageDialog(SignUpFrame.this, "User already exists. Please choose a different username.", "User Exists", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Save user information
                try {
                    saveUserInformation(username, password);
                    dispose(); // Close the sign-up window
                } catch (IOException ioException) {
                    JOptionPane.showMessageDialog(SignUpFrame.this, "Error saving user information. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean validateInput(String username, String password) {
        // Username and password cannot be empty
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and password cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Username must be between 4 and 16 characters and contain no special characters
        if (username.length() < 4 || username.length() > 16 || !username.matches("^[a-zA-Z0-9]+$")) {
            JOptionPane.showMessageDialog(this, "Username must be between 4 and 16 characters and contain no special characters.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Password must be between 8 and 20 characters
        if (password.length() < 8 || password.length() > 20) {
            JOptionPane.showMessageDialog(this, "Password must be between 8 and 20 characters.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean userExists(String username) {
        File usersDir = new File("users");
        File userFile = new File(usersDir, username + ".csv");
        return userFile.exists();
    }

    private void saveUserInformation(String username, String password) throws IOException {
        File usersDir = new File("users");
        if (!usersDir.exists()) {
            boolean created = usersDir.mkdirs();
            if (!created) {
                JOptionPane.showMessageDialog(this, "Failed to create user directory.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        File userFile = new File(usersDir, username + ".csv");
        try (FileWriter writer = new FileWriter(userFile)) {
            writer.write("Username,Password\n"); // Optional: Write header
            writer.write(username + "," + password + "\n");

            // Registration successful, automatically log the user in
            autoLogin(username);
        } catch (IOException ioException) {
            JOptionPane.showMessageDialog(this, "Error saving user information. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void autoLogin(String username) {
        // Close the SignUpFrame
        dispose();

        // Show a message that the user is being logged in
        JOptionPane.showMessageDialog(null, "Registration successful! Logging you in...", "Welcome", JOptionPane.INFORMATION_MESSAGE);

        // Open the MenuFrame as the main application window
        MenuFrame menuFrame = new MenuFrame(); // Assuming MenuFrame does not require username in constructor
        menuFrame.setVisible(true);
    }
}
