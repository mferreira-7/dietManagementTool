package main.app.view;

import main.app.utils.CSVReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static main.app.utils.Constants.*;

public class MenuFrame extends JFrame {
    private JButton calculatorButton;
    private JButton dailyButton;
    private JButton reportButton;
    private JButton signOutButton;
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
        this.currentUser = currentUser;
    }

    private void initializeComponents() {
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setResizable(false);
        setTitle("Diet Management Tool - Menu");

        calculateLabel = new JLabel("Nutrition Recommendation Calculator -->");
        dailyLabel = new JLabel("Daily Food Intake -->");
        reportLabel = new JLabel("Generate Report -->");

        calculatorButton = new JButton("Proceed");
        CalculatorButtonListener calculatorButtonListener = new CalculatorButtonListener(this);
        calculatorButton.addActionListener(calculatorButtonListener);

        dailyButton = new JButton("Proceed");
        DailyButtonListener dailyButtonListener = new DailyButtonListener(this);
        dailyButton.addActionListener(dailyButtonListener);

        reportButton = new JButton("Proceed");
        ReportButtonListener reportButtonListener = new ReportButtonListener(this);
        reportButton.addActionListener(reportButtonListener);

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
        Font labelFont = new Font(FONT_NAME, Font.PLAIN, LABEL_FONT_SIZE);
        Font fieldFont = new Font(FONT_NAME, Font.PLAIN, FIELD_FONT_SIZE);
        Color buttonColor = BUTTON_COLOR;
        Color buttonTextColor = BUTTON_TEXT_COLOR;

        calculateLabel.setFont(labelFont);
        dailyLabel.setFont(labelFont);
        reportLabel.setFont(labelFont);

        calculatorButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        calculatorButton.setFont(labelFont);
        calculatorButton.setBackground(buttonColor);
        calculatorButton.setForeground(buttonTextColor);
        calculatorButton.setFocusPainted(false);

        dailyButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        dailyButton.setFont(labelFont);
        dailyButton.setBackground(buttonColor);
        dailyButton.setForeground(buttonTextColor);
        dailyButton.setFocusPainted(false);

        reportButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        reportButton.setFont(labelFont);
        reportButton.setBackground(buttonColor);
        reportButton.setForeground(buttonTextColor);
        reportButton.setFocusPainted(false);

        // Apply styles to the sign out button (optional)
        signOutButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        signOutButton.setFont(new Font(FONT_NAME, Font.PLAIN, LABEL_FONT_SIZE));
        signOutButton.setBackground(BUTTON_COLOR);
        signOutButton.setForeground(BUTTON_TEXT_COLOR);
        signOutButton.setFocusPainted(false);
    }

    private void setLayout() {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);

        JPanel calculatorOptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        calculatorOptionPanel.add(calculateLabel);
        calculatorOptionPanel.add(calculatorButton);
        panel.add(calculatorOptionPanel, gbc);

        gbc.gridy++;

        JPanel dailyOptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dailyOptionPanel.add(dailyLabel);
        dailyOptionPanel.add(dailyButton);
        panel.add(dailyOptionPanel, gbc);

        gbc.gridy++;

        JPanel reportOptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        reportOptionPanel.add(reportLabel);
        reportOptionPanel.add(reportButton);
        panel.add(reportOptionPanel, gbc);

        // Position the "Sign Out" button at the last row of the grid
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE; // Place the button at the next available row
        panel.add(signOutButton, gbc); // Add the "Sign Out" button to the panel


        setContentPane(panel);
    }

    public String getCurrentUser(){return currentUser;}


    // getting rid of the main as it wouldn't be called
/*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuFrame::new);
    }*/
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

class ReportButtonListener implements ActionListener {
    private final MenuFrame menuFrame;

    public ReportButtonListener(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CSVReader.GenerateReport(menuFrame.getCurrentUser());
    }
}