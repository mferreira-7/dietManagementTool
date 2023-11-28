package main.app.view;

import main.app.model.Person;
import main.app.viewmodel.NutritionViewModel;
import main.app.viewmodel.UserInfoViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfoFrame extends JFrame {
    private JTextField ageField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JTextField weightField;
    private JTextField heightField;
    private JRadioButton lowActivityRadioButton;
    private JRadioButton moderateActivityRadioButton;
    private JRadioButton highActivityRadioButton;

    private JComboBox<String> dietaryPreferenceComboBox;
    private JComboBox<String> goalComboBox;
    private JButton submitButton;
    private UserInfoViewModel viewModel;

    public UserInfoFrame() {
        viewModel = new UserInfoViewModel(); // Create an instance of the ViewModel

        initializeComponents();
        setLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }



    private void initializeComponents() {
        ageField = new JTextField(20);
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        weightField = new JTextField(20);
        heightField = new JTextField(20);
        dietaryPreferenceComboBox = new JComboBox<>(new String[]{"Neither", "Vegan", "Vegetarian"});
        goalComboBox = new JComboBox<>(new String[]{"Lose weight", "Maintain weight", "Gain muscles"});
        submitButton = new JButton("Submit");
        lowActivityRadioButton = new JRadioButton("Low (1.2)");
        moderateActivityRadioButton = new JRadioButton("Moderate (1.5)");
        highActivityRadioButton = new JRadioButton("High (1.8)");

        ButtonGroup activityLevelGroup = new ButtonGroup();
        activityLevelGroup.add(lowActivityRadioButton);
        activityLevelGroup.add(moderateActivityRadioButton);
        activityLevelGroup.add(highActivityRadioButton);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validating and parsing age
                    String ageText = ageField.getText();
                    if (!ageText.matches("\\d+")) { // Check if the text contains only digits
                        JOptionPane.showMessageDialog(UserInfoFrame.this, "Please enter a valid number for age.");
                        return;
                    }
                    int age = Integer.parseInt(ageText);

                    // Checking if age is a reasonable number
                    if (age < 18 || age > 119) {
                        JOptionPane.showMessageDialog(UserInfoFrame.this, "Please enter a reasonable age.");
                        return;
                    }

                    // Check if gender is selected
                    if (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected()) {
                        JOptionPane.showMessageDialog(UserInfoFrame.this, "Please select a gender.");
                        return;
                    }
                    int gender = maleRadioButton.isSelected() ? 0 : 1;

                    // Validating and parsing weight
                    String weightText = weightField.getText();
                    if (!weightText.matches("\\d+(\\.\\d+)?")) { // Check if the text is a valid floating point number
                        JOptionPane.showMessageDialog(UserInfoFrame.this, "Please enter a valid number for weight.");
                        return;
                    }
                    double weight = Double.parseDouble(weightText);

                    if (weight < 20 || weight > 200) {
                        JOptionPane.showMessageDialog(UserInfoFrame.this, "Please enter a reasonable weight");
                        return;
                    }

                    // Validating and parsing height
                    String heightText = heightField.getText();
                    if (!heightText.matches("\\d+(\\.\\d+)?")) {
                        JOptionPane.showMessageDialog(UserInfoFrame.this, "Please enter a valid number for height.");
                        return;
                    }
                    double height = Double.parseDouble(heightText);

                    if (height <= 60 || height >= 250) {
                        JOptionPane.showMessageDialog(UserInfoFrame.this, "Please enter a reasonable height.");
                        return;
                    }

                    // Determine the selected activity level
                    double activityLevel;
                    if (lowActivityRadioButton.isSelected()) {
                        activityLevel = 1.2;
                    } else if (moderateActivityRadioButton.isSelected()) {
                        activityLevel = 1.5;
                    } else if (highActivityRadioButton.isSelected()) {
                        activityLevel = 1.8;
                    } else {
                        JOptionPane.showMessageDialog(UserInfoFrame.this, "Please select an activity level.");
                        return;
                    }

                    int dietaryPreference = dietaryPreferenceComboBox.getSelectedIndex();
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
                    JOptionPane.showMessageDialog(UserInfoFrame.this, "Submitted the form successfully!");
                    Person person = viewModel.createPerson();

                    // Open NutritionView with the created person
                    NutritionViewModel nutritionViewModel = new NutritionViewModel(person);
                    new NutritionView(nutritionViewModel);

                    dispose(); // Close the current UserInfoFrame
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(UserInfoFrame.this, "An unexpected error occurred, please check your inputs.");
                }
            }
        });
        applyStyles();
    }

    private void applyStyles() {
        Font labelFont = new Font("Roboto", Font.PLAIN, 18);
        Font fieldFont = new Font("Roboto", Font.PLAIN, 16);
        Color buttonColor = new Color(100, 181, 246);
        Color buttonTextColor = Color.BLACK;

        // Apply styles to components
        ageField.setFont(fieldFont);
        maleRadioButton.setFont(labelFont);
        femaleRadioButton.setFont(labelFont);
        weightField.setFont(fieldFont);
        heightField.setFont(fieldFont);
        dietaryPreferenceComboBox.setFont(fieldFont);
        goalComboBox.setFont(fieldFont);
        lowActivityRadioButton.setFont(labelFont);
        moderateActivityRadioButton.setFont(labelFont);
        highActivityRadioButton.setFont(labelFont);

        submitButton.setFont(new Font("Roboto", Font.BOLD, 22));
        submitButton.setBackground(buttonColor);
        submitButton.setForeground(buttonTextColor);
        submitButton.setFocusPainted(false);
    }

    private void setLayout() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create and style each row with its own JPanel
        mainPanel.add(createRowPanel(new JLabel("Age:"), ageField));
        mainPanel.add(createRowPanel(new JLabel("Gender:"), createGenderPanel()));
        mainPanel.add(createRowPanel(new JLabel("Weight (kg):"), weightField));
        mainPanel.add(createRowPanel(new JLabel("Height (cm):"), heightField));
        mainPanel.add(createRowPanel(new JLabel("Activity Level:"), createActivityLevelPanel()));
        mainPanel.add(createRowPanel(new JLabel("Dietary Requirement:"), dietaryPreferenceComboBox));
        mainPanel.add(createRowPanel(new JLabel("Goal:"), goalComboBox));

        // Submit button styled and placed
        submitButton.setPreferredSize(new Dimension(200, 50));
        submitButton.setFont(new Font("Roboto", Font.BOLD, 22));
        submitButton.setBackground(new Color(100, 181, 246));
        submitButton.setForeground(Color.BLACK);
        submitButton.setFocusPainted(false);

        JPanel submitPanel = new JPanel();
        submitPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        submitPanel.add(submitButton);

        mainPanel.add(submitPanel);

        // Set the main panel as the content pane of the frame
        setContentPane(mainPanel);

        // Adjust the window to fit the preferred size and layouts of its subcomponents
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private JPanel createRowPanel(JComponent label, JComponent field) {
        JPanel rowPanel = new JPanel(new BorderLayout());
        rowPanel.add(label, BorderLayout.WEST);
        rowPanel.add(field, BorderLayout.CENTER);
        return rowPanel;
    }

    private JPanel createGenderPanel() {
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        return genderPanel;
    }

    private JPanel createActivityLevelPanel() {
        JPanel activityLevelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        activityLevelPanel.add(lowActivityRadioButton);
        activityLevelPanel.add(moderateActivityRadioButton);
        activityLevelPanel.add(highActivityRadioButton);
        return activityLevelPanel;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserInfoFrame());
    }
}
