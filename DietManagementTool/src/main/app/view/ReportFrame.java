package main.app.view;

import main.app.utils.CSVReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

public class ReportFrame extends JFrame {
    private JButton reportButton;
    private String currentUser;
    private JLabel reportLabel;

    public ReportFrame(String currentUser) {
        this.currentUser = currentUser;
        initializeComponents();
        setLayoutCustom(); // Renamed to avoid confusion with the existing setLayout method of JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(700, 500);
    }

    private void initializeComponents() {
        setTitle("Diet Management Tool - Report Generation");

        reportLabel = new JLabel("Nutrition Recommendation Calculator ->");
        reportButton = new JButton("Generate");
        ReportButtonListener reportButtonListener = new ReportButtonListener(this);
        reportButton.addActionListener(reportButtonListener);

        applyStyles();
    }

    private void applyStyles() {
        Font buttonFont = new Font("Arial", Font.BOLD, 12);
        reportButton.setBackground(Color.BLACK);
        reportButton.setForeground(Color.WHITE);
        reportButton.setFont(buttonFont);
        reportButton.setOpaque(true);
        reportButton.setBorderPainted(false);
        reportButton.setFocusPainted(false);
    }

    private void setLayoutCustom() {
        getContentPane().setLayout(new BorderLayout());

        // Panels for date selection
        JPanel startDatePanel = new JPanel();
        JPanel endDatePanel = new JPanel();
        startDatePanel.setLayout(new FlowLayout());
        endDatePanel.setLayout(new FlowLayout());

        // Labels
        startDatePanel.add(new JLabel("Start Date:"));
        endDatePanel.add(new JLabel("End Date:"));

        // Date JComboBoxes for start date
        JComboBox<Integer> startDay = generateNumbers(1, 31);
        JComboBox<Integer> startMonth = generateNumbers(1, 12);
        JComboBox<Integer> startYear = generateNumbers(1900, Calendar.getInstance().get(Calendar.YEAR));

        // Date JComboBoxes for end date
        JComboBox<Integer> endDay = generateNumbers(1, 31);
        JComboBox<Integer> endMonth = generateNumbers(1, 12);
        JComboBox<Integer> endYear = generateNumbers(1900, Calendar.getInstance().get(Calendar.YEAR));

        // Adding JComboBoxes to panels
        startDatePanel.add(startDay);
        startDatePanel.add(startMonth);
        startDatePanel.add(startYear);
        endDatePanel.add(endDay);
        endDatePanel.add(endMonth);
        endDatePanel.add(endYear);

        // Main panel to hold everything
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(startDatePanel);
        mainPanel.add(endDatePanel);
        mainPanel.add(reportButton);

        getContentPane().add(mainPanel, BorderLayout.CENTER);
        pack(); // Adjusts the frame size to fit the components
        setLocationRelativeTo(null); // Center the frame
        setResizable(true); // Allow resizing
    }

    private JComboBox<Integer> generateNumbers(int start, int end) {
        Integer[] numbers = new Integer[end - start + 1];
        for (int i = start; i <= end; i++) {
            numbers[i - start] = i;
        }
        return new JComboBox<>(numbers);
    }

    public String getCurrentUser() {
        return currentUser;
    }
}

class ReportButtonListener implements ActionListener {
    private final ReportFrame reportFrame;

    public ReportButtonListener(ReportFrame reportFrame) {
        this.reportFrame = reportFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Assuming CSVReader.GenerateReport is a static method that takes a String parameter
         CSVReader.GenerateReport(reportFrame.getCurrentUser());
        System.out.println("Generate Report for: " + reportFrame.getCurrentUser());
    }
}
