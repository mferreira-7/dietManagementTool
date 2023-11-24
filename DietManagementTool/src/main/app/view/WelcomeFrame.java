package main.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class WelcomeFrame extends JFrame {
    private JButton startButton;

    public WelcomeFrame() {
        initializeComponents();
        setLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        startButton = new JButton("Start");

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

        JLabel welcomeLabel = new JLabel("Welcome to Our App!");
        welcomeLabel.setFont(new Font("Serif", Font.ITALIC, 18));

        panel.add(welcomeLabel);
        panel.add(startButton);

        setContentPane(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(WelcomeFrame::new);
    }
}
