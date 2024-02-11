package main.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static main.app.utils.Constants.*;

public class LoginFrame extends JFrame {
    private JButton startButton;
    private JLabel usernameLabel;
    private JTextField usernameField;


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
        usernameLabel = new JLabel("Enter username to proceed", JLabel.CENTER);
        startButton = new JButton("Log in");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuFrame();
                dispose();
            }
        });

        applyStyles();
    }

    private void applyStyles() {
        Font labelFont = new Font(FONT_NAME, Font.PLAIN, LABEL_FONT_SIZE);
        Font fieldFont = new Font(FONT_NAME, Font.PLAIN, FIELD_FONT_SIZE);
        Color buttonColor = BUTTON_COLOR;
        Color buttonTextColor = BUTTON_TEXT_COLOR;

        usernameLabel.setFont(labelFont);
        usernameField.setFont(fieldFont);

        startButton.setFont(labelFont);
        startButton.setBackground(buttonColor);
        startButton.setForeground(buttonTextColor);
        startButton.setFocusPainted(false);
        startButton.setPreferredSize(new Dimension(150, 40));
        startButton.setMaximumSize(new Dimension(150, 40));
    }


    private void setLayout() {
        setSize(800, 500);
        setResizable(false);
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        panel.add(usernameLabel, gbc);
        gbc.gridy++;
        panel.add(usernameField, gbc);
        gbc.gridy++;
        panel.add(startButton, gbc);

        setContentPane(panel);
        setLocationRelativeTo(null);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame welcomeScreen = new LoginFrame();
            welcomeScreen.setVisible(true);
        });
    }
}