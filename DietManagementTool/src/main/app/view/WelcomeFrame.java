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
        startButton = new JButton("Start");
        startButton.setFont(new Font("Arial", Font.BOLD, 22));
        startButton.setBackground(new Color(255, 140, 0)); // Custom orange color
        startButton.setForeground(Color.BLACK);
        startButton.setFocusPainted(false);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the UserInfoFrame when the "Start" button is clicked
                new MenuPage();
                // Close the current WelcomeFrame
                dispose();
            }
        });
    }

    private void setLayout() {
        JPanel panel = new JPanel(new FlowLayout());
        setSize(600, 500);
        welcomeLabel = new JLabel("Welcome to Diet Management App", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));

        // Placeholder for feature icons and text
        featurePanel = new JPanel();
        featurePanel.setOpaque(false); // Use the background of the content pane
        featurePanel.setLayout(new GridLayout(2, 2, 10, 10));
        // Add your feature icons and text here
        featurePanel.add(new JLabel("Click the start button to proceed"));

        panel.add(welcomeLabel, BorderLayout.NORTH);
        panel.add(featurePanel, BorderLayout.CENTER);
        panel.add(startButton, BorderLayout.SOUTH);

        setContentPane(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WelcomeFrame welcomeScreen = new WelcomeFrame();
            welcomeScreen.setVisible(true);
        });
    }
}
