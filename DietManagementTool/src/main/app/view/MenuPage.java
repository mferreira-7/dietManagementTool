package main.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPage extends JFrame {
    private JButton calculatorButton;

    public MenuPage() {
        initializeComponents();
        setLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        setTitle("Diet Management Tool - Menu");

        calculatorButton = new JButton("Calculator");
        calculatorButton.setFont(new Font("Roboto", Font.BOLD, 22));
        calculatorButton.setBackground(new Color(100, 181, 246));
        calculatorButton.setForeground(Color.BLACK);
        calculatorButton.setFocusPainted(false);

        calculatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the UserInfoFrame when the Calculator button is clicked
                new UserInfoFrame();
                // Close the current MenuPage
                dispose();
            }
        });
    }

    private void setLayout() {
        // Use GridBagLayout for flexible component placement
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 255)); // White background
        setSize(600, 500);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;

        // Create a sub-panel with FlowLayout to hold the label and button next to each other
        JPanel subPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0)); // 10 is the gap between components
        JLabel calculateLabel = new JLabel("Please Click to Calculate your Info -->");
        calculateLabel.setFont(new Font("Roboto", Font.PLAIN, 18));

        // Style the calculatorButton
        calculatorButton.setPreferredSize(new Dimension(200, 50));
        calculatorButton.setFont(new Font("Roboto", Font.BOLD, 22));
        calculatorButton.setBackground(new Color(100, 181, 246));
        calculatorButton.setForeground(Color.BLACK);
        calculatorButton.setFocusPainted(false);

        // Add the label and button to the sub-panel
        subPanel.add(calculateLabel);
        subPanel.add(calculatorButton);

        // Add the sub-panel to the main panel with constraints
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // This is to ensure horizontal stretch
        gbc.weighty = 1.0; // This is to ensure vertical stretch
        panel.add(subPanel, gbc);

        setContentPane(panel);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuPage::new);
    }
}
