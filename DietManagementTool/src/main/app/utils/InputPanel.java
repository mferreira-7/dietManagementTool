package main.app.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class InputPanel extends JPanel {
    // UtilVars
    MealType[] comboOpt = {
            MealType.Other, MealType.Breakfast, MealType.Lunch, MealType.Dinner
    };

    // Creating the fields
    JLabel foodPrompt = new JLabel("Enter the name of the meal / ingredient / food-item you want to add: ");
    JLabel mealTypePrompt = new JLabel("Enter the type of the meal this is: ");
    private JTextField foodInput = new JTextField(60);
    private JComboBox<MealType> mealTypeComboBox = new JComboBox<>(comboOpt);

    // Constructor
    public InputPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initialise();
        setupPreviewText(); // Set up preview text logic for foodInput
    }

    public String getUserSearch() {
        return foodInput.getText().trim(); // Trim leading and trailing spaces
    }

    public MealType getMealType() {
        return (MealType) mealTypeComboBox.getSelectedItem(); // Directly return the selected item
    }

    public void clearInputField() {
        foodInput.setText(""); // Clear the food input field
    }

    // Function which adds the text inputs and prompts to the panel
    private void initialise() {
        mealTypeComboBox.setEditable(false);
        add(mealTypePrompt);
        add(mealTypeComboBox);

        foodInput.setEditable(true);
        add(foodPrompt);
        add(foodInput);
    }

    // Setup preview text functionality for foodInput
    private void setupPreviewText() {
        final String previewText = "500g of chicken";
        foodInput.setText(previewText);
        foodInput.setForeground(Color.GRAY); // Set the preview text color to gray

        foodInput.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (foodInput.getText().equals(previewText)) {
                    foodInput.setText(""); // Clear the preview text when field gains focus
                    foodInput.setForeground(Color.BLACK); // Reset text color to default
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (foodInput.getText().isEmpty()) {
                    foodInput.setForeground(Color.GRAY); // Reapply gray color for preview text
                    foodInput.setText(previewText); // Restore preview text if field is empty when losing focus
                }
            }
        });
    }
}
