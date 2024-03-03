package main.app.view;

import main.app.utils.CSVReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Calendar;

import static main.app.utils.Constants.SCREEN_HEIGHT;
import static main.app.utils.Constants.SCREEN_WIDTH;

public class ReportFrame extends JFrame {
    private JButton reportButton;
    private String currentUser;
    private JLabel reportLabel;
    private JButton viewReportButton;
    private JButton backButton;

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
        backButton = new JButton("Back");
        viewReportButton = new JButton("View Report"); // Initialize the view report button

        applyStyles();

        backButton.addActionListener(new ActionListener() { // Add action listener to back button
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MenuFrame menuFrame = new MenuFrame(currentUser);
                menuFrame.setVisible(true);
            }
        });
        getRootPane().setDefaultButton(reportButton);

        viewReportButton.addActionListener(e -> {
            try {
                File htmlFile = new File("src/main/user_reports/" + currentUser + ".html");
                if (htmlFile.exists()) { // Check if the file exists
                    Desktop.getDesktop().browse(htmlFile.toURI()); // Properly convert the file path to URI and open it
                    // Display a success message
                    JOptionPane.showMessageDialog(this, "Report is being opened in your default browser.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Report file not found: " + htmlFile.getAbsolutePath(), "File Not Found", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Failed to open report.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void applyStyles() {
        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        // Style the report button
        reportButton.setBackground(Color.BLACK);
        reportButton.setForeground(Color.WHITE);
        reportButton.setFont(buttonFont);
        reportButton.setOpaque(true);
        reportButton.setBorderPainted(false);
        reportButton.setFocusPainted(false);

        // Style the back button
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(buttonFont);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);

        // Style the view report button
        viewReportButton.setBackground(Color.BLACK);
        viewReportButton.setForeground(Color.WHITE);
        viewReportButton.setFont(buttonFont);
        viewReportButton.setOpaque(true);
        viewReportButton.setBorderPainted(false);
        viewReportButton.setFocusPainted(false);
    }

    private void setLayoutCustom() {

        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setResizable(false);
        // Assuming SCREEN_HEIGHT and SCREEN_WIDTH are defined and represent the size of the screen
        int topPanelHeight = (int) (SCREEN_HEIGHT * 0.10); // Calculate 10% of the screen height for the top panel

        // Set the main layout to BorderLayout
        getContentPane().setLayout(new BorderLayout());

        // Create the top panel with BorderLayout for the logo
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, topPanelHeight)); // Set the preferred size for the top panel

        // Add a logo to the top panel
        JLabel logoLabel = new JLabel(); // Create a label to hold the logo
        logoLabel.setHorizontalAlignment(JLabel.CENTER); // Set the logo to align center
        URL logoUrl = getClass().getResource("/main/resources/Images/Image_5.png");
        ImageIcon logoIcon = new ImageIcon(logoUrl);
        Image logoImage = logoIcon.getImage();
        // Scale the Logo to fit the application window or a specific size
        Image scaledLogo = logoImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(scaledLogo);
        logoLabel.setIcon(scaledLogoIcon); // Add the logo to the label
        topPanel.add(logoLabel, BorderLayout.CENTER); // Add the label to the top panel


        JPanel rightPanel = new JPanel(new GridBagLayout()); // This panel will have the GridBagLayout
        JPanel leftPanel = new JPanel(new BorderLayout()); // This panel will just contain an image

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH; // Change to NORTH if you want components to be aligned to the top
        gbc.insets = new Insets(10, 0, 10, 0);

        // Panels for date selection
        JPanel startDatePanel = new JPanel();
        JPanel endDatePanel = new JPanel();
        JPanel buttonPanel = new JPanel(); // Panel to hold buttons
        startDatePanel.setLayout(new FlowLayout());
        endDatePanel.setLayout(new FlowLayout());
        buttonPanel.setLayout(new FlowLayout()); // Set layout for button panel

        // Labels
        startDatePanel.add(new JLabel("Start Date:"));
        endDatePanel.add(new JLabel("End Date:"));

        Calendar currentCalendar = Calendar.getInstance();
        int currentYear = currentCalendar.get(Calendar.YEAR);
        int currentMonth = currentCalendar.get(Calendar.MONTH) + 1; // +1 because Calendar.MONTH is zero-based
        int currentDay = currentCalendar.get(Calendar.DAY_OF_MONTH);

        // Initialize JComboBoxes for date selection
        JComboBox<Integer> startDay = generateNumbers(1, 31);
        JComboBox<Integer> startMonth = generateNumbers(1, 12);
        JComboBox<Integer> startYear = generateNumbers(1900, currentYear - 1);
        startYear.setSelectedItem(currentYear - 1); // Set startYear to last year

        JComboBox<Integer> endDay = generateNumbers(1, 31);
        endDay.setSelectedItem(currentDay); // Set endDay to today
        JComboBox<Integer> endMonth = generateNumbers(1, 12);
        endMonth.setSelectedItem(currentMonth); // Set endMonth to current month
        JComboBox<Integer> endYear = generateNumbers(1900, currentYear);
        endYear.setSelectedItem(currentYear); // Set endYear to this year

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

        // Create an empty border for spacing
        int topSpacing = 60; // Adjust the value to increase or decrease the space
        mainPanel.setBorder(BorderFactory.createEmptyBorder(topSpacing, 0, 0, 0));

        ReportButtonListener reportButtonListener = new ReportButtonListener(this, startDay, startMonth, startYear, endDay, endMonth, endYear);
        reportButton.addActionListener(reportButtonListener);

        // Add report and back buttons to button panel
        buttonPanel.add(reportButton);
        buttonPanel.add(backButton);
        buttonPanel.add(viewReportButton);

        rightPanel.add(startDatePanel, gbc);
        gbc.gridy++;

        rightPanel.add(endDatePanel, gbc);
        gbc.gridy++;

        rightPanel.add(buttonPanel, gbc);
        gbc.gridy++;

        try {
            URL imageUrl = getClass().getResource("/main/resources/Images/Image_4.png"); //file path
            if (imageUrl != null) {
                ImageIcon originalIcon = new ImageIcon(imageUrl);
                Image originalImage = originalIcon.getImage();

                // Scale the image to fit the application window or a specific size
                Image scaledImage = originalImage.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);

                JLabel imageLabel = new JLabel(scaledIcon);
                imageLabel.setHorizontalAlignment(JLabel.CENTER);
                leftPanel.add(imageLabel, BorderLayout.CENTER);
                leftPanel.setOpaque(false);
            } else {
                System.err.println("Image file not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error loading the image.");
        }


        getContentPane().add(topPanel, BorderLayout.NORTH); // Top panel at the top
        getContentPane().add(rightPanel, BorderLayout.EAST);
        getContentPane().add(leftPanel, BorderLayout.CENTER); // Add the right panel to the center (which will effectively be the right side)

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