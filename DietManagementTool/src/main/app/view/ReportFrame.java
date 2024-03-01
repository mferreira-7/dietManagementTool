package main.app.view;

import main.app.utils.CSVReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.DateTimeException;
import java.time.LocalDate;
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

        ReportButtonListener reportButtonListener = new ReportButtonListener(this, startDay, startMonth, startYear, endDay, endMonth, endYear);
        reportButton.addActionListener(reportButtonListener);

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
    private final JComboBox<Integer> startDay, startMonth, startYear;
    private final JComboBox<Integer> endDay, endMonth, endYear;

    public ReportButtonListener(ReportFrame reportFrame, JComboBox<Integer> startDay, JComboBox<Integer> startMonth, JComboBox<Integer> startYear, JComboBox<Integer> endDay, JComboBox<Integer> endMonth, JComboBox<Integer> endYear) {
        this.reportFrame = reportFrame;
        this.startDay = startDay;
        this.startMonth = startMonth;
        this.startYear = startYear;
        this.endDay = endDay;
        this.endMonth = endMonth;
        this.endYear = endYear;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            LocalDate startDate = LocalDate.of(startYear.getItemAt(startYear.getSelectedIndex()), startMonth.getItemAt(startMonth.getSelectedIndex()), startDay.getItemAt(startDay.getSelectedIndex()));
            LocalDate endDate = LocalDate.of(endYear.getItemAt(endYear.getSelectedIndex()), endMonth.getItemAt(endMonth.getSelectedIndex()), endDay.getItemAt(endDay.getSelectedIndex()));

            // Validate that startDate is before or equal to endDate
            if (startDate.isAfter(endDate)) {
                JOptionPane.showMessageDialog(reportFrame, "Start date must be before end date.", "Date Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Now, pass these LocalDate objects to your CSVReader method
            CSVReader.GenerateReport(reportFrame.getCurrentUser(), startDate, endDate);
            System.out.println("Generated Report for: " + reportFrame.getCurrentUser() + " From: " + startDate + " To: " + endDate);
        } catch (DateTimeException ex) {
            JOptionPane.showMessageDialog(reportFrame, "Invalid date provided.", "Date Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}