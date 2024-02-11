package main.app.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static main.app.utils.Constants.*;

public class MenuFrame extends JFrame {
    private JButton calculatorButton;
    private JButton dailyButton;
    private JLabel calculateLabel;
    private JLabel dailyLabel;

    public MenuFrame() {
        initializeComponents();
        setLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        setTitle("Diet Management Tool - Menu");

        calculateLabel = new JLabel("Nutrition Recommendation Calculator -->");
        dailyLabel = new JLabel("Daily Food Intake -->");

        calculatorButton = new JButton("Proceed");
        CalculatorButtonListener calculatorButtonListener = new CalculatorButtonListener(this);
        calculatorButton.addActionListener(calculatorButtonListener);

        dailyButton = new JButton("Proceed");
        DailyButtonListener dailyButtonListener = new DailyButtonListener(this);
        dailyButton.addActionListener(dailyButtonListener);

        applyStyles();
    }

    private void applyStyles() {
        Font labelFont = new Font(FONT_NAME, Font.PLAIN, LABEL_FONT_SIZE);
        Font fieldFont = new Font(FONT_NAME, Font.PLAIN, FIELD_FONT_SIZE);
        Color buttonColor = BUTTON_COLOR;
        Color buttonTextColor = BUTTON_TEXT_COLOR;

        calculateLabel.setFont(labelFont);
        dailyLabel.setFont(labelFont);

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

    }

    private void setLayout() {
        setSize(800, 500);
        setResizable(false);
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;

        JPanel calculatorOptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        calculatorOptionPanel.add(calculateLabel);
        calculatorOptionPanel.add(calculatorButton);

        panel.add(calculatorOptionPanel, gbc);
        gbc.gridy++;

        JPanel dailyOptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dailyOptionPanel.add(dailyLabel);
        dailyOptionPanel.add(dailyButton);

        panel.add(dailyOptionPanel, gbc);

        setContentPane(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MenuFrame::new);
    }
}

class CalculatorButtonListener implements ActionListener {
    private final MenuFrame menuFrame;

    public CalculatorButtonListener(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new CalculatorFrame();
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
        new DailyFrame();
        menuFrame.dispose();
    }
}

