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
    private JLabel calculateLabel;
    private JLabel dailyLabel;
    private JLabel reportLabel;

    public MenuFrame() {
        initializeComponents();
        setLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
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

    }

    private void setLayout() {
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

        gbc.gridy++;

        JPanel reportOptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        reportOptionPanel.add(reportLabel);
        reportOptionPanel.add(reportButton);
        panel.add(reportOptionPanel, gbc);

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

class ReportButtonListener implements ActionListener {
    private final MenuFrame menuFrame;

    public ReportButtonListener(MenuFrame menuFrame) {
        this.menuFrame = menuFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CSVReader.GenerateReport("vlad123");
    }
}