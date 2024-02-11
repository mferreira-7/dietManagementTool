package main.app.view;

import main.app.utils.*;
import main.app.model.Person;
import main.app.utils.Food;

import javax.swing.*;
import java.awt.*;

public class DailyFrame extends JFrame {

    InputPanel inputPanel;
    ControlPanel controlPanel;
    MealDisplayPanel mealDisplayPanel;
    Person appendingPerson;
    Meal mealToAdd = new Meal(MealType.Other);

    public DailyFrame() {
        Person person = new Person(0, 25, 70, 175, 1.5, 0, 1);
        DailyFrame amf = new DailyFrame();
        amf.createComponents(person);
    }


    public void createComponents(Person personToChange) {

        //This is the person that is going to get the meals added to
        this.appendingPerson = personToChange;

        inputPanel = new InputPanel();
        inputPanel.setBackground(Color.lightGray);
        inputPanel.setPreferredSize(new Dimension(500,80));
        add(inputPanel, BorderLayout.NORTH);

        mealDisplayPanel = new MealDisplayPanel();
        add(mealDisplayPanel, BorderLayout.CENTER);

        controlPanel = new ControlPanel(this, inputPanel,mealDisplayPanel);
        add(controlPanel, BorderLayout.SOUTH);

        setMealType();//setting it to the one displayed

        setSize(700, 365);
        setVisible(true);
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    }

    public void setMealType(){
        mealToAdd.setMealType(inputPanel.getMealType());
    }

    public String foodList(){
        return mealToAdd.displayFoods();
    }

    public boolean addFoodToMeal()
    {
        boolean successFlag = true;
        Food foodToAdd = FoodItemSearchAPI.getNutritionInfo(inputPanel.getUserSearch());

        if(foodToAdd.getName() == "NULL"){
            return false;
        }

        mealToAdd.addFood(foodToAdd);

        return successFlag;
    }

    public static void main(String[] args){
        Person person = new Person(0, 25, 70, 175, 1.5, 0, 1);
        DailyFrame amf = new DailyFrame();
        amf.createComponents(person);
    }
}
