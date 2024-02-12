package main.app.utils;

import main.app.view.DailyFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {
    private final DailyFrame dailyFrame;
    private final InputPanel inputPanel;
    private final MealDisplayPanel mealDisplayPanel;


    //Constructor initialises all the buttons and takes in all the panel classes
    public ControlPanel(DailyFrame mainFrame, InputPanel userInputPanel, MealDisplayPanel mealDisplayPanel) {
        this.dailyFrame = mainFrame;
        this.inputPanel = userInputPanel;
        this.mealDisplayPanel = mealDisplayPanel;
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(initialiseAddButton());
        add(setMealTypeButton());
    }


    //creating the add button's characteristics
    private JButton initialiseAddButton() {
        JButton button = new JButton("Search & add");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean successFlag = dailyFrame.addFoodToMeal();

                if(successFlag){
                    JOptionPane.showMessageDialog(null,
                            "Food item added",
                            "Notice - Added item",
                            JOptionPane.INFORMATION_MESSAGE);

                    mealDisplayPanel.foodItemLabels.setText(dailyFrame.foodList());

                }else {
                    JOptionPane.showMessageDialog(null,
                            "Food not found",
                            "Notice - Item not added",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        return button;
    }

    private JButton setMealTypeButton(){
        JButton button = new JButton("Set Meal Type");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dailyFrame.setMealType();

                JOptionPane.showMessageDialog(null,
                        "Meal Type Set",
                        "Notice - Type Set",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        return button;
    }
}
