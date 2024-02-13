package main.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import static main.app.utils.Constants.*;

public class LoginFrame extends JFrame {
    private JButton startButton;
    private JButton signUpButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel newUserLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        initializeComponents();
        setLayout();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeComponents() {
        setTitle("Diet Management Tool");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        usernameLabel = new JLabel("Enter username to proceed", JLabel.CENTER);
        passwordLabel = new JLabel("Enter password", JLabel.CENTER);
        newUserLabel = new JLabel("New User?", JLabel.CENTER);
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
        Font labelFont = new Font(FONT_NAME, Font.PLAIN, LABEL_FONT_SIZE);
        Font fieldFont = new Font(FONT_NAME, Font.PLAIN, FIELD_FONT_SIZE);
        Color buttonColor = BUTTON_COLOR;
        Color buttonTextColor = BUTTON_TEXT_COLOR;

        usernameLabel.setFont(labelFont);
        passwordLabel.setFont(labelFont);
        newUserLabel.setFont(labelFont);
        usernameField.setFont(fieldFont);
        passwordField.setFont(fieldFont);

        startButton.setFont(labelFont);
        signUpButton.setFont(labelFont);
        startButton.setBackground(buttonColor);
        signUpButton.setBackground(buttonColor);
        startButton.setForeground(buttonTextColor);
        signUpButton.setForeground(buttonTextColor);
        startButton.setFocusPainted(false);
        signUpButton.setFocusPainted(false);
        startButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        signUpButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        startButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        signUpButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
    }

    private void setLayout() {
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setResizable(false);
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);

        panel.add(usernameLabel, gbc);
        gbc.gridy++;
        panel.add(usernameField, gbc);
        gbc.gridy++;
        panel.add(passwordLabel, gbc);
        gbc.gridy++;
        panel.add(passwordField, gbc);
        gbc.gridy++;
        panel.add(startButton, gbc);
        gbc.gridy++;
        panel.add(newUserLabel, gbc);
        gbc.gridy++;
        panel.add(signUpButton, gbc);

        setContentPane(panel);
        setLocationRelativeTo(null);
    }

    private void initializeSignUpButtonAction() {
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());

                // Username length check
                if (username.length() < 4 || username.length() > 18) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Username must be between 4 and 18 characters.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit the method to prevent further execution
                }

                // Username content validation
                if (!username.matches("^[a-zA-Z0-9]+$")) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Username can only contain letters and numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit the method to prevent further execution
                }

                // Password length check
                if (password.length() < 8 || password.length() > 16) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Password must be between 8 and 16 characters.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit the method to prevent further execution
                }

                // Check if user already exists
                if (userExists(username)) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "User already exists. Please log in or choose a different username.", "User Exists", JOptionPane.ERROR_MESSAGE);
                    return; // Exit the method to prevent the creation of a new user with the same username
                }

                // Proceed with saving the new user information
                try {
                    saveUserInformation(username, password);
                    JOptionPane.showMessageDialog(LoginFrame.this, "User registered successfully. Logging you in...", "Registration Successful", JOptionPane.INFORMATION_MESSAGE);

                    // Automatically log the user in after successful sign-up
                    loginUser(username);

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Error saving user information: " + ioException.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private boolean userExists(String username) {
        File usersDir = new File("M:\\Year 2\\Team Project\\23-24_CE201-col_team-50\\DietManagementTool\\src\\main\\users");
        File userFile = new File(usersDir, username + ".csv");
        return userFile.exists();
    }

    private void loginUser(String username) {
        // Logic to proceed with login, e.g., opening the main application window or dashboard
        JOptionPane.showMessageDialog(this, "Welcome, " + username + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
        new MenuFrame(); // Assuming MenuFrame is the main window of your application after login
        dispose(); // Close the login window
    }


    private void saveUserInformation(String username, String password) throws IOException {
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(LoginFrame.this, "Username and password cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        File usersDir = new File("M:\\Year 2\\Team Project\\23-24_CE201-col_team-50\\DietManagementTool\\src\\main\\users");
        System.out.println("Users directory path: " + usersDir.getAbsolutePath());

        if (!usersDir.exists()) {
            boolean dirCreated = usersDir.mkdir();
            System.out.println("Users directory creation attempt: " + dirCreated);
            if (!dirCreated) {
                JOptionPane.showMessageDialog(LoginFrame.this, "Failed to create directory: " + usersDir.getAbsolutePath(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Create a CSV file named after the username
        File userFile = new File(usersDir, username + ".csv");
        System.out.println("User file path: " + userFile.getAbsolutePath());

        try (FileWriter writer = new FileWriter(userFile, false)) { // false to overwrite the file if it already exists
            writer.append("Username,").append("Password\n"); // Optional: Write header
            writer.append(username).append(",").append(password).append("\n");
            System.out.println("User information written to file: " + userFile.getName());
        } catch (IOException ioException) {
            System.out.println("Error writing user information to file: " + userFile.getName());
            throw ioException; // Rethrow the exception to be caught in the calling method
        }

        // Confirmation message
        if (userFile.exists() && userFile.length() > 0) {
            JOptionPane.showMessageDialog(this, "User information saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to save user information.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and password cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int loginResult = verifyUser(username, password);
        switch (loginResult) {
            case 0: // User file does not exist
                JOptionPane.showMessageDialog(this, "User does not exist. Please sign up.", "User Not Found", JOptionPane.ERROR_MESSAGE);
                break;
            case 1: // Username and password match
                JOptionPane.showMessageDialog(this, "Login successful.", "Welcome", JOptionPane.INFORMATION_MESSAGE);
                // Logic to proceed with login, e.g., opening a new frame
                new MenuFrame();
                dispose(); // Dispose the login frame
                break;
            case -1: // User exists but password does not match
                JOptionPane.showMessageDialog(this, "Incorrect username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    private int verifyUser(String username, String password) {
        File usersDir = new File("M:\\Year 2\\Team Project\\23-24_CE201-col_team-50\\DietManagementTool\\src\\main\\users");
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
            JOptionPane.showMessageDialog(this, "An error occurred while verifying user information.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return -1; // User exists but password does not match
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
