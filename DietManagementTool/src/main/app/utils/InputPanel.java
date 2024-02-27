package main.app.utils;

import javax.swing.*;

public class InputPanel extends JPanel {
    //UtilVars
    MealType[] comboOpt = {main.app.utils.MealType.Other, main.app.utils.MealType.Breakfast, main.app.utils.MealType.Lunch,
            main.app.utils.MealType.Dinner};

    //Creating the fields
    JLabel foodPrompt = new JLabel("Enter the name of the meal / " +
            "ingredient / " +
            "food-item you " +
            "want to add: ");
    JLabel mealTypePrompt = new JLabel("Enter the type of the meal this is: ");
    private JTextField foodInput = new JTextField(60);
    private JComboBox<MealType> mealTypeComboBox = new JComboBox<>(comboOpt);

    //Constructor
    public InputPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initialise();
    }

    public String getUserSearch() {
        return foodInput.getText();
    }

    public MealType getMealType() {
        int selected = mealTypeComboBox.getSelectedIndex();
        return mealTypeComboBox.getItemAt(selected);
    }

    public void clearInputField() {
        foodInput.setText("");
    }

    //function which adds the text inputs and prompts to the panel
    private void initialise() {

        mealTypeComboBox.setEditable(false);
        add(mealTypePrompt);
        add(mealTypeComboBox);

        foodInput.setEditable(true);
        add(foodPrompt);
        add(foodInput);

    }
}
