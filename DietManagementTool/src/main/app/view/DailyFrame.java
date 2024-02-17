package main.app.view;

import main.app.utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static main.app.utils.Constants.SCREEN_HEIGHT;
import static main.app.utils.Constants.SCREEN_WIDTH;

public class DailyFrame extends JFrame {

    InputPanel inputPanel;
    ControlPanel controlPanel;
    MealDisplayPanel mealDisplayPanel;
    Meal mealToAdd = new Meal(MealType.Other);
    String username;
    private JButton backButton;

    public DailyFrame(String username) {
        initializeComponents();
        setLayout();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        this.username = username;
    }


    private void initializeComponents() {
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setResizable(false);
        setLayout(new GridBagLayout());

        backButton = new JButton("Back"); // Initialize the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Dispose of the DailyFrame
                new MenuFrame().setVisible(true); // Open the MenuFrame
            }
        });
        applyStyles();
    }



    private void applyStyles() {
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setFocusPainted(false);
    }

    private void setLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        inputPanel = new InputPanel();
        inputPanel.setBackground(Color.lightGray);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(inputPanel, gbc);

        mealDisplayPanel = new MealDisplayPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(mealDisplayPanel, gbc);

        controlPanel = new ControlPanel(this, inputPanel, mealDisplayPanel);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(controlPanel, gbc);

        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.add(backButton);
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.NONE;
        add(backButtonPanel, gbc);

        revalidate();
        repaint();
        setMealType();

        setLocationRelativeTo(null);
    }

    public void setMealType() {
        mealToAdd.setMealType(inputPanel.getMealType());
    }

    public String foodList() {
        return mealToAdd.displayFoods();
    }

    public boolean addFoodToMeal() {
        boolean successFlag = true;
        Food foodToAdd = FoodItemSearchAPI.getNutritionInfo(inputPanel.getUserSearch());

        if (foodToAdd.getName() == "NULL") {
            return false;
        }

        mealToAdd.addFood(foodToAdd);

        return successFlag;
    }

    public void saveMeal(){
        ReadWriteFoodData writer = new ReadWriteFoodData(username);
        writer.createAndWriteToCSV(writer.mealToString(mealToAdd));
        dispose();
    }

    public static void main(String[] args) {
        new DailyFrame("vlad123");
    }
}
