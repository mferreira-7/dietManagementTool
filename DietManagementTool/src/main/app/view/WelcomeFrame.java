package main.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeFrame extends JFrame {
    private JButton startButton;
    private JLabel welcomeLabel;
    private JPanel featurePanel;

    public WelcomeFrame() {
        initializeComponents();
        setLayout();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeComponents() {
        setTitle("Diet Management Tool");
        startButton = new JButton("Touch to Start");
        startButton.setFont(new Font("Roboto", Font.BOLD, 22));
        startButton.setBackground(new Color(100, 181, 246));
        startButton.setForeground(Color.BLACK);
        startButton.setFocusPainted(false);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuPage();
                dispose();
            }
        });
    }

    private void setLayout() {
        // Set the main panel's layout to GridBagLayout for flexible positioning
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 255)); // White background
        setSize(600, 500);

        // Set up constraints for components
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Welcome label
        welcomeLabel = new JLabel("Welcome to Diet Management App", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Roboto", Font.BOLD, 24));
        panel.add(welcomeLabel, constraints);

        // Feature panel (if needed, otherwise remove)
        featurePanel = new JPanel(new GridLayout(2, 2, 10, 10));
        featurePanel.setOpaque(false);
        // Add feature icons and text here

        // Adjust constraints for the button to expand it horizontally
        constraints.weightx = 0.5;
        constraints.fill = GridBagConstraints.NONE; // Do not resize the button
        constraints.weighty = 1; // Give weight to push the button up

        // Start button
        startButton.setPreferredSize(new Dimension(200, 50));
        startButton.setMaximumSize(new Dimension(200, 50));

        // Add the button panel with constraints to the center
        panel.add(startButton, constraints);

        setContentPane(panel);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WelcomeFrame welcomeScreen = new WelcomeFrame();
            welcomeScreen.setVisible(true);
        });
    }
}
