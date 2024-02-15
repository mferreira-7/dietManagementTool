package main.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SignUpFrame extends JFrame {
    private JTextField fullNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public SignUpFrame() {
        setTitle("Sign Up Page");
        setSize(700, 500); // Set the frame size
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set the layout
        setLayout(new BorderLayout());

        // Create the left panel for the image
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(400,350));
        leftPanel.add(new JLabel(new ImageIcon("/Users/segzzy4real/Downloads/image.jpg")));

        // Create the top panel for the logo
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE); // Set the background color to match the image
        topPanel.setPreferredSize(new Dimension(getWidth(), (int) (getHeight() * 0.10)));

        // Load the logo image and add it to the top panel
        ImageIcon logoIcon = new ImageIcon("/Users/segzzy4real/Downloads/logo.png"); // Adjust the path as necessary
        JLabel logoLabel = new JLabel(logoIcon);
        topPanel.add(logoLabel);

        // Create the form panel with GridBagLayout for the registration form
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Initialize components like text fields and labels
        initializeComponents();

        // Add components to the form panel with GridBagConstraints
        gbc.anchor = GridBagConstraints.WEST; // Align labels to the left
        formPanel.add(new JLabel("Fullname:"), gbc);
        gbc.gridy++;
        formPanel.add(fullNameField, gbc);
        gbc.gridy++;
        formPanel.add(new JLabel("Username:"), gbc);
        gbc.gridy++;
        formPanel.add(usernameField, gbc);
        gbc.gridy++;
        formPanel.add(new JLabel("Password:"), gbc);
        gbc.gridy++;
        formPanel.add(passwordField, gbc);
        gbc.gridy++;

        formPanel.add(registerButton, gbc);


        // Set the main panel to split the frame into left and right
        JPanel mainPanel = new JPanel(new GridLayout(1, 2)); // 1 row, 2 columns
        mainPanel.add(leftPanel);
        mainPanel.add(formPanel);

        // Add the main panel to the frame
        add(mainPanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Display the frame
        setVisible(true);


    }


    private void initializeComponents() {
        // Setup the text fields to be larger
        int fieldWidth = 70;
        fullNameField = new JTextField(fieldWidth);
        usernameField = new JTextField(fieldWidth);
        passwordField = new JPasswordField(fieldWidth);

        // Style the register button
        registerButton = new JButton("Register");
        registerButton.setBackground(Color.BLACK);
        registerButton.setForeground(Color.WHITE);
        registerButton.setOpaque(true);
        registerButton.setBorderPainted(false);
        registerButton.setFocusPainted(false);

        add(new JLabel("Fullname:"));
        add(fullNameField);
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

        registerButton.setBackground(Color.BLACK);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
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
