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
        calculatorButton = new JButton("Calculator");

        calculatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the UserInfoFrame when the "Start" button is clicked
                new UserInfoFrame();
                // Close the current WelcomeFrame
                dispose();
            }
        });
    }

    private void setLayout() {

        JPanel panel = new JPanel(new FlowLayout());
        JLabel calculateLabel = new JLabel("Please Click to Calculate your Info -->");
        setSize(600, 500);
        panel.add(calculateLabel);
        panel.add(calculatorButton, BorderLayout.NORTH);
        setContentPane(panel);


    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuPage::new);
    }
}
