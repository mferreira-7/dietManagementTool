package main.app.view;

import main.app.model.Person;
import main.app.viewmodel.CalculatorViewModel;
import main.app.viewmodel.NutritionRecommendationViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import static main.app.utils.Constants.*;

public class CalculatorFrame extends JFrame {
    private JTextField ageField;
//    private JRadioButton maleRadioButton;
//    private JRadioButton femaleRadioButton;
    private JTextField weightField;
    private JTextField heightField;
//    private JRadioButton lowActivityRadioButton;
//    private JRadioButton moderateActivityRadioButton;
//    private JRadioButton highActivityRadioButton;
    public JComboBox<String> activityLevelComboBox;
    public JComboBox<String> genderComboBox;

    private JComboBox<String> dietComboBox;

    private JComboBox<String> goalComboBox;
    private JButton submitButton;
    protected CalculatorViewModel viewModel;
    private JButton backButton;
    protected String username;

    public CalculatorFrame(String username) {
        viewModel = new CalculatorViewModel();
        initializeComponents();
        setLayout();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        this.username = username;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
                new MenuFrame(username);
            }
        });


    }


    private void initializeComponents() {
        setSize(700, 500);
        setResizable(false);
        setTitle("Diet Management Tool - Nutrition Recommendation");

        ageField = new JTextField(20);
        weightField = new JTextField(20);
        heightField = new JTextField(20);
        dietComboBox = new JComboBox<>(new String[]{"Neither", "Vegan", "Vegetarian"});
        goalComboBox = new JComboBox<>(new String[]{"Lose weight", "Maintain weight", "Gain muscles"});
        submitButton = new JButton("Submit");
        String[] genderOptions = {"Male", "Female"};
        String[] activityLevels = {"Low", "Moderate", "High"};

        genderComboBox = new JComboBox<>(genderOptions);
        activityLevelComboBox = new JComboBox<>(activityLevels);

//        ButtonGroup activityLevelGroup = new ButtonGroup();
//        activityLevelGroup.add(lowActivityRadioButton);
//        activityLevelGroup.add(moderateActivityRadioButton);
//        activityLevelGroup.add(highActivityRadioButton);

//        ButtonGroup genderGroup = new ButtonGroup();
//        genderGroup.add(maleRadioButton);
//        genderGroup.add(femaleRadioButton);

        backButton = new JButton("Back"); // Initialize the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Dispose of the CalculatorFrame
                new MenuFrame(username).setVisible(true); // Open the MenuFrame
            }
        });

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validating and parsing age
                    String ageText = ageField.getText();
                    if (!ageText.matches("\\d+")) { // Check if the text contains only digits
                        JOptionPane.showMessageDialog(CalculatorFrame.this, "Please enter a valid number for age.");
                        return;
                    }
                    int age = Integer.parseInt(ageText);

                    // Checking if age is a reasonable number
                    if (age < 18 || age > 119) {
                        JOptionPane.showMessageDialog(CalculatorFrame.this, "Please enter a reasonable age.");
                        return;
                    }


                    // Determine the selected gender
                    String selectedGender = (String) genderComboBox.getSelectedItem();
                    int gender = -1; // Initialize with an invalid value

                    if ("Male".equals(selectedGender)) {
                        gender = 0; // Assuming 0 represents male in your ViewModel
                    } else if ("Female".equals(selectedGender)) {
                        gender = 1; // Assuming 1 represents female in your ViewModel
                    } else {
                        JOptionPane.showMessageDialog(CalculatorFrame.this, "Please select a gender.");
                        return;
                    }

                    // Check if gender is selected
//                    if (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected()) {
//                        JOptionPane.showMessageDialog(CalculatorFrame.this, "Please select a gender.");
//                        return;
//                    }
//                    int gender = maleRadioButton.isSelected() ? 0 : 1;




                    // Validating and parsing weight
                    String weightText = weightField.getText();
                    if (!weightText.matches("\\d+(\\.\\d+)?")) { // Check if the text is a valid floating point number
                        JOptionPane.showMessageDialog(CalculatorFrame.this, "Please enter a valid number for weight.");
                        return;
                    }
                    double weight = Double.parseDouble(weightText);

                    if (weight < 20 || weight > 200) {
                        JOptionPane.showMessageDialog(CalculatorFrame.this, "Please enter a reasonable weight");
                        return;
                    }

                    // Validating and parsing height
                    String heightText = heightField.getText();
                    if (!heightText.matches("\\d+(\\.\\d+)?")) {
                        JOptionPane.showMessageDialog(CalculatorFrame.this, "Please enter a valid number for height.");
                        return;
                    }
                    double height = Double.parseDouble(heightText);

                    if (height <= 60 || height >= 250) {
                        JOptionPane.showMessageDialog(CalculatorFrame.this, "Please enter a reasonable height.");
                        return;
                    }


                    // Determine the selected activity level
                    double activityLevel = 0;
                    String selectedActivity = (String) activityLevelComboBox.getSelectedItem();

                    if (selectedActivity != null) {
                        if ("Low".equals(selectedActivity)) {
                            activityLevel = 1.2;
                        } else if ("Moderate".equals(selectedActivity)) {
                            activityLevel = 1.5;
                        } else if ("High".equals(selectedActivity)) {
                            activityLevel = 1.8;
                        } else {
                            JOptionPane.showMessageDialog(CalculatorFrame.this, "Please select a valid activity level.");
                            return;
                        }
                    } else {
                        JOptionPane.showMessageDialog(CalculatorFrame.this, "Please select an activity level.");
                        return;
                    }

                    // Determine the selected activity level
//                    double activityLevel;
//                    if (lowActivityRadioButton.isSelected()) {
//                        activityLevel = 1.2;
//                    } else if (moderateActivityRadioButton.isSelected()) {
//                        activityLevel = 1.5;
//                    } else if (highActivityRadioButton.isSelected()) {
//                        activityLevel = 1.8;
//                    } else {
//                        JOptionPane.showMessageDialog(CalculatorFrame.this, "Please select an activity level.");
//                        return;
//                    }

                    int dietaryPreference = dietComboBox.getSelectedIndex();
                    int goal = goalComboBox.getSelectedIndex();

                    // Pass the information to the ViewModel
                    viewModel.setAge(age);
                    viewModel.setGender(gender);
                    viewModel.setWeight(weight);
                    viewModel.setHeight(height);
                    viewModel.setActivityLevel(activityLevel);
                    viewModel.setDietaryPreference(dietaryPreference);
                    viewModel.setGoal(goal);

                    // Assuming you have a method in the ViewModel to create a Person instance
                    viewModel.createPerson();
                    JOptionPane.showMessageDialog(CalculatorFrame.this, "Submitted the form successfully!");
                    Person person = viewModel.createPerson();

                    // Open NutritionView with the created person
                    NutritionRecommendationViewModel nutritionRecommendationViewModel = new NutritionRecommendationViewModel(person);
                    new NutritionRecommendationFrame(nutritionRecommendationViewModel, username);

                    dispose(); // Close the current UserInfoFrame
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(CalculatorFrame.this, "An error occurred: " + ex.getMessage());
                }
            }
        });
        applyStyles();
    }

    private void applyStyles() {
        Font buttonFont = new Font("Arial", Font.BOLD, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 16);

        ageField.setFont(fieldFont);
        genderComboBox.setFont(fieldFont);
        weightField.setFont(fieldFont);
        heightField.setFont(fieldFont);
        goalComboBox.setFont(fieldFont);
        goalComboBox.setFont(fieldFont);
        activityLevelComboBox.setFont(fieldFont);

        // Style the submitButton button
        submitButton.setFont(buttonFont);
        submitButton.setBackground(Color.BLACK);
        submitButton.setForeground(Color.WHITE);
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        submitButton.setFocusPainted(false);

        // Style the backButton button
        backButton.setFont(buttonFont);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
    }

    private void setLayout() {

        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50); // Top, left, bottom, right padding

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

        getContentPane().add(topPanel, BorderLayout.NORTH); // Top panel at the top


        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

//        JPanel mainPanel = new JPanel();
//        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create and style each row with its own JPanel
        getContentPane().add(createRowPanel(new JLabel("Age:"), ageField), gbc);
        getContentPane().add(createRowPanel(new JLabel("Gender:"), genderComboBox), gbc);
        getContentPane().add(createRowPanel(new JLabel("Weight (kg):"), weightField), gbc);
        getContentPane().add(createRowPanel(new JLabel("Height (cm):"), heightField), gbc);
        getContentPane().add(createRowPanel(new JLabel("Activity Level:"),activityLevelComboBox), gbc);   //));
        getContentPane().add(createRowPanel(new JLabel("Dietary Requirement:"), dietComboBox), gbc);
        getContentPane().add(createRowPanel(new JLabel("Goal:"), goalComboBox), gbc);

        JPanel submitPanel = new JPanel();
        submitButton.setPreferredSize(new Dimension(100, 30)); // Adjust size as needed
        submitPanel.add(submitButton);
        gbc.fill = GridBagConstraints.NONE; // Do not stretch the button
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        getContentPane().add(submitPanel, gbc);


        JPanel backPanel = new JPanel();
        backPanel.add(backButton);
        getContentPane().add(backPanel);


//        mainPanel.add(submitPanel);
//        mainPanel.add(backPanel);
//        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        pack();
    }



    private JPanel createRowPanel(JComponent label, JComponent field) {
        JPanel rowPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 2, 2, 2); // Top, left, bottom, right padding
//        rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
        gbc.anchor = GridBagConstraints.WEST;
//        label.setPreferredSize(new Dimension(100, 20)); // Adjust size as needed
        rowPanel.add(label, gbc);

        gbc.anchor = GridBagConstraints.EAST;
//        rowPanel.add(Box.createRigidArea(new Dimension(2, 0))); // Space between label and field
        rowPanel.add(field, gbc);
        return rowPanel;
    }

//    private JPanel createGenderPanel() {
//        JPanel genderPanel = new JPanel(new FlowLayout());
//        genderPanel.add(maleRadioButton);
//        genderPanel.add(femaleRadioButton);
//        return genderPanel;
//    }
//
//    private JPanel createActivityLevelPanel() {
//        JPanel activityLevelPanel = new JPanel(new FlowLayout());
//        activityLevelPanel.add(lowActivityRadioButton);
//        activityLevelPanel.add(moderateActivityRadioButton);
//        activityLevelPanel.add(highActivityRadioButton);
//        return activityLevelPanel;
//    }

}
/*  Won't work without a username variable
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorFrame());


        public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorFrame("username"));
    }
}*/
