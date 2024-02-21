package main.app.utils;

import javax.swing.*;

public class MealDisplayPanel extends JPanel {

    JLabel mealTypePrompt = new JLabel("Food items in this meal: ");
    JLabel foodItemLabels = new JLabel("");

    public MealDisplayPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initialise();
    }

    public boolean noFoodCheck(){
        return foodItemLabels.getText().isEmpty();
    }

    private void initialise(){
        add(mealTypePrompt);
        add(foodItemLabels);
    }

}
