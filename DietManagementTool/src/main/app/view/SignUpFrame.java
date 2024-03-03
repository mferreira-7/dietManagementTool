package main.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import static main.app.utils.Constants.SCREEN_HEIGHT;
import static main.app.utils.Constants.SCREEN_WIDTH;

public class SignUpFrame extends JFrame {
    private JTextField fullNameField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton backButton;

    public SignUpFrame() {
        setTitle("Sign Up Page");
        setSize(700, 500); // Set the frame size
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Assuming SCREEN_HEIGHT and SCREEN_WIDTH are defined and represent the size of the screen
        int topPanelHeight = (int) (SCREEN_HEIGHT * 0.10); // Calculate 10% of the screen height for the top panel

        // Set the layout
        getContentPane().setLayout(new BorderLayout()); // Use BorderLayout for the main layout

        // Create the top panel for the logo
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


        // Create the left panel for the image
        JPanel rightPanel = new JPanel(new GridBagLayout());
        JPanel leftPanel = new JPanel(new BorderLayout()); // This panel will just contain an image

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
            } else {
                System.err.println("Image file not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading the image.");
        }


        // Create the right panel with GridBagLayout for the registration form
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 0;
        gbc.anchor = GridBagConstraints.NORTH;

        // Initialize components like text fields and labels
        initializeComponents();

        // Set the default button to be the register button
        getRootPane().setDefaultButton(registerButton);

        // Add components to the form panel with GridBagConstraints
        rightPanel.add(new JLabel("Fullname:"), gbc);
        gbc.gridy++;
        rightPanel.add(fullNameField, gbc);
        gbc.gridy++;
        rightPanel.add(new JLabel("Username:"), gbc);
        gbc.gridy++;
        rightPanel.add(usernameField, gbc);
        gbc.gridy++;
        rightPanel.add(new JLabel("Password:"), gbc);
        gbc.gridy++;

        rightPanel.add(passwordField, gbc);
        gbc.gridy++;
        rightPanel.add(registerButton, gbc);
        gbc.gridy++;
        rightPanel.add(backButton, gbc);
        //gbc.gridy++;


        // Add the panels to the frame
        getContentPane().add(topPanel, BorderLayout.NORTH); // Top panel at the top
        getContentPane().add(rightPanel, BorderLayout.EAST); // Add the left panel to the left side
        getContentPane().add(leftPanel, BorderLayout.CENTER); // Add the right panel to the center (which will effectively be the right side)


        // Display the frame
        setVisible(true);
        setLocationRelativeTo(null);

    }


    private void initializeComponents() {
        // Setup the text fields to be larger
        int fieldWidth = 18;
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


        registerButton.addActionListener(e -> performRegistration());

        registerButton.setBackground(Color.BLACK);
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setFont(new Font("Arial", Font.BOLD, 18));

        backButton = new JButton("Back");

        // Style the back button (Optional)
        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("Arial", Font.BOLD, 18));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the SignUpFrame
                new LoginFrame().setVisible(true); // Open the LoginFrame
            }
        });
    }

    private void performRegistration() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String fullName = fullNameField.getText().trim();

        if (!validateInput(username, password, fullName)) {
            return;
        }

        if (userExists(username)) {
            JOptionPane.showMessageDialog(this, "User already exists. Please choose a different username.", "User Exists", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            saveUserInformation(username, password);
            autoLogin(username);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving user information. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateInput(String username, String password, String fullName) {
        if (username.isEmpty() || password.isEmpty() || fullName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (username.length() < 4 || username.length() > 16 || !username.matches("^[a-zA-Z0-9_]+$")) {
            JOptionPane.showMessageDialog(this, "Username must be between 4 and 16 characters and can only contain alphanumeric characters and underscores.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
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
            String fullName = fullNameField.getText().trim();

            writer.write("Username,Password,Fullname\n");
            writer.write(username + "," + password + "," + fullName + "\n");
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
        EventQueue.invokeLater(() -> {
            MenuFrame menuFrame = new MenuFrame(username);
            menuFrame.setVisible(true);
        });
    }
}
