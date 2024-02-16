package main.app.model;

import main.app.utils.Food;
import main.app.utils.Meal;
import main.app.utils.MealType;

import java.util.Vector;

public class Person {
    private int gender; // 0 - male, 1 - "female"
    private int age;
    private double weight; // in kilograms
    private double height; // in centimeters
    private double activityLevel; // 1.2 - low, 1.5 - moderate, 1.8 - high
    private int dietaryPreference; // 0 - neither, 1 - vegan, 2 - vegetarian
    private int goal; // 0 - lose weight, 1 - maintain weight, 2 - gain muscles,
    private Vector<Meal> allMeals = new Vector<>();

    public Person(int gender, int age, double weight, double height, double activityLevel, int dietaryPreference, int goal) {
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.activityLevel = activityLevel;
        this.dietaryPreference = dietaryPreference;
        this.goal = goal;
    }

    public int getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getActivityLevel() {
        return activityLevel;
    }

    public int getDietaryPreference() {
        return dietaryPreference;
    }

    public int getGoal() {
        return goal;
    }


    public Vector getAllMeals(){return allMeals;}

    public void printAllMeals(){
        //UtilVars
        int count = 1;

        System.out.println("Meals that have been added:");
        for (Meal meal : allMeals) {
            System.out.println("Meal No." + count + ": " + (meal.getMealType()).toString() + "on the date " +
                    meal.getDate());
        }
    }

    public Meal getMeal(int mealNum){
        //Util Vars
        Meal nullMeal = new Meal(MealType.NULL);

        if (!(allMeals.get(mealNum)).isMealNull() && allMeals.size() >= mealNum){
            return allMeals.get(mealNum);
        }else {
            System.out.println("Meal not found\n");
            return nullMeal; // if it returns a null meal, the code calling it should be able to handle a null meal
        }

    }

    public void deleteMeal(int mealToDelete){

        if (!(allMeals.get(mealToDelete)).isMealNull() && allMeals.size() >= mealToDelete){
            allMeals.remove(mealToDelete);
        }else {
            System.out.println("Meal not found, deletion aborted\n");
        }
    }

    public void addMealToPerson(Meal mealInput) {

        allMeals.add(mealInput);

    }

    public void createMeal(MealType mealType){

    }
}



