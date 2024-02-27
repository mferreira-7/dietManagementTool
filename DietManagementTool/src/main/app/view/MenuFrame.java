package main.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import static main.app.utils.Constants.SCREEN_HEIGHT;
import static main.app.utils.Constants.SCREEN_WIDTH;

public class MenuFrame extends JFrame {
    private JButton calculatorButton;
    private JButton dailyButton;
    private JButton reportButton;
    private JButton signOutButton;
    private JLabel welcomeLabel;
    private JLabel optionsLable;
    private JLabel calculateLabel;
    private JLabel dailyLabel;
    private JLabel reportLabel;
    private String currentUser;

    public MenuFrame(String currentUser) {
        initializeComponents();
        setLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(700, 500);
        this.currentUser = currentUser;
    }

    private void initializeComponents() {
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setResizable(false);
        setTitle("Diet Management Tool - Menu");

        Font labelFont = new Font("Arial", Font.BOLD, 18);

        welcomeLabel = new JLabel("WELCOME ON BOARD");
        welcomeLabel.setFont(labelFont);
        optionsLable = new JLabel("Choose Options Below: ");
        optionsLable.setFont(labelFont);


        calculateLabel = new JLabel("Nutrition Recommendation Calculator ->");
        calculatorButton = new JButton("Proceed");
        CalculatorButtonListener calculatorButtonListener = new CalculatorButtonListener(this);
        calculatorButton.addActionListener(calculatorButtonListener);

        dailyLabel = new JLabel("Daily Food Intake ->");
        dailyButton = new JButton("Proceed");
        DailyButtonListener dailyButtonListener = new DailyButtonListener(this);
        dailyButton.addActionListener(dailyButtonListener);

        reportLabel = new JLabel("Generate Report ->");
        reportButton = new JButton("Proceed");
        ReportMenuButtonListener reportMenuButtonListener = new ReportMenuButtonListener(this);
        reportButton.addActionListener(reportMenuButtonListener);


        signOutButton = new JButton("Sign Out"); // Initialize the sign out button
        signOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Dispose of the MenuFrame
                new LoginFrame().setVisible(true); // Open the LoginFrame
            }
        });

        applyStyles();
    }

    private void applyStyles() {
        Font buttonFont = new Font("Arial", Font.BOLD, 12);
        Font labelFont = new Font("Arial", Font.PLAIN, 14);


        calculateLabel.setFont(labelFont);
        dailyLabel.setFont(labelFont);
        reportLabel.setFont(labelFont);

        // Style the calculator button
        calculatorButton.setBackground(Color.BLACK);
        calculatorButton.setForeground(Color.WHITE);
        calculatorButton.setFont(buttonFont);
        calculatorButton.setOpaque(true);
        calculatorButton.setBorderPainted(false);
        calculatorButton.setFocusPainted(false);

        // Style the daily button
        dailyButton.setBackground(Color.BLACK);
        dailyButton.setForeground(Color.WHITE);
        dailyButton.setFont(buttonFont);
        dailyButton.setOpaque(true);
        dailyButton.setBorderPainted(false);
        dailyButton.setFocusPainted(false);

        // Style the report button
        reportButton.setBackground(Color.BLACK);
        reportButton.setForeground(Color.WHITE);
        reportButton.setFont(buttonFont);
        reportButton.setOpaque(true);
        reportButton.setBorderPainted(false);
        reportButton.setFocusPainted(false);

        // Style the sign-out button
        signOutButton.setBackground(Color.GRAY);
        signOutButton.setForeground(Color.WHITE);
        signOutButton.setFont(buttonFont);
        signOutButton.setOpaque(true);
        signOutButton.setBorderPainted(false);
        signOutButton.setFocusPainted(false);

    }

    private void setLayout() {

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
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);


        JPanel welcomeOptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        welcomeOptionPanel.add(welcomeLabel);
        rightPanel.add(welcomeOptionPanel, gbc);
        gbc.gridy++;

        JPanel optionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        optionPanel.add(optionsLable);
        rightPanel.add(optionPanel, gbc);
        gbc.gridy++;

        JPanel calculatorOptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        calculatorOptionPanel.add(calculateLabel);
        calculatorOptionPanel.add(calculatorButton);
        rightPanel.add(calculatorOptionPanel, gbc);
        gbc.gridy++;

        JPanel dailyOptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dailyOptionPanel.add(dailyLabel);
        dailyOptionPanel.add(dailyButton);
        rightPanel.add(dailyOptionPanel, gbc);
        gbc.gridy++;

        JPanel reportOptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        reportOptionPanel.add(reportLabel);
        reportOptionPanel.add(reportButton);
        rightPanel.add(reportOptionPanel, gbc);

        // Position the "Sign Out" button at the last row of the grid
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE; // Place the button at the next available row
        rightPanel.add(signOutButton, gbc); // Add the "Sign Out" button to the panel


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

    public String getCurrentUser() {
        return currentUser;
    }
}


class CalculatorButtonListener implements ActionListener {
    private final MenuFrame menuFrame;

    public CalculatorButtonListener(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new CalculatorFrame(menuFrame.getCurrentUser());
        menuFrame.dispose();
    }
}

class DailyButtonListener implements ActionListener {
    private final MenuFrame menuFrame;

    public DailyButtonListener(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new DailyFrame(menuFrame.getCurrentUser());
        menuFrame.dispose();
    }
}

class ReportMenuButtonListener implements ActionListener {
    private final MenuFrame menuFrame;

    public ReportMenuButtonListener(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new ReportFrame(menuFrame.getCurrentUser());
        menuFrame.dispose();
    }
}