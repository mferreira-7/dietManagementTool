package main.app.view;

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
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(UserInfoFrame.this, "An unexpected error occurred, please check your inputs.");
                }
            }
        });
    }

    private void setLayout() {
        JPanel panel = new JPanel(new GridLayout(11, 2, 10, 10));
        setSize(600, 500);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);

        panel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        panel.add(genderPanel);

        panel.add(new JLabel("Weight (kg):"));
        panel.add(weightField);

        panel.add(new JLabel("Height (cm):"));
        panel.add(heightField);

        panel.add(new JLabel("Activity Level:"));
        JPanel activityLevelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        activityLevelPanel.add(lowActivityRadioButton);
        activityLevelPanel.add(moderateActivityRadioButton);
        activityLevelPanel.add(highActivityRadioButton);
        panel.add(activityLevelPanel);

        panel.add(new JLabel("Dietary Requirement:"));
        panel.add(dietaryPreferenceComboBox);

        panel.add(new JLabel("Goal:"));
        panel.add(goalComboBox);

        // New panel for the submit button
        JPanel submitButtonPanel = new JPanel(new FlowLayout());
        submitButtonPanel.add(submitButton);

        // Add an empty label for the left cell of the grid if needed
        panel.add(new JLabel(""));
        panel.add(submitButtonPanel);


        setContentPane(panel);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserInfoFrame());
    }
}
