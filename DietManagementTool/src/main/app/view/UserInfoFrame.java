package main.app.view;

import main.app.viewmodel.UserInfoViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserInfoFrame extends JFrame {
    private JTextField ageField;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JTextField weightField;
    private JTextField heightField;
    private JTextField activityLevelField;
    private JComboBox<String> dietaryPreferenceComboBox;
    private JComboBox<String> goalComboBox;
    private JButton submitButton;
    private UserInfoViewModel viewModel;

    public UserInfoFrame() {
        viewModel = new UserInfoViewModel(); // Create an instance of the ViewModel

        initializeComponents();
        setLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        ageField = new JTextField(20);
        maleRadioButton = new JRadioButton("Male");
        femaleRadioButton = new JRadioButton("Female");
        weightField = new JTextField(20);
        heightField = new JTextField(20);
        activityLevelField = new JTextField(20);
        dietaryPreferenceComboBox = new JComboBox<>(new String[]{"Neither", "Vegan", "Vegetarian"});
        goalComboBox = new JComboBox<>(new String[]{"Lose weight", "Maintain weight", "Gain muscles"});
        submitButton = new JButton("Submit");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the submission of user info
                int age = Integer.parseInt(ageField.getText());
                int gender = maleRadioButton.isSelected() ? 0 : 1;
                double weight = Double.parseDouble(weightField.getText());
                double height = Double.parseDouble(heightField.getText());
                double activityLevel = Double.parseDouble(activityLevelField.getText());
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
            }
        });
    }

    private void setLayout() {
        JPanel panel = new JPanel();
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Gender:"));
        panel.add(maleRadioButton);
        panel.add(femaleRadioButton);
        panel.add(new JLabel("Weight (kg):"));
        panel.add(weightField);
        panel.add(new JLabel("Height (m):"));
        panel.add(heightField);
        panel.add(new JLabel("Activity Level:"));
        panel.add(activityLevelField);
        panel.add(new JLabel("Dietary Preference:"));
        panel.add(dietaryPreferenceComboBox);
        panel.add(new JLabel("Goal:"));
        panel.add(goalComboBox);
        panel.add(submitButton);

        setContentPane(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserInfoFrame());
    }
}
