package main.app.model;

public class Person {
    private int gender; // 0 - male, 1 - "female"
    private int age;
    private double weight; // in kilograms
    private double height; // in centimeters
    private double activityLevel; // 1.2 - low, 1.5 - moderate, 1.8 - high
    private int dietaryPreference; // 0 - neither, 1 - vegan, 2 - vegetarian
    private int goal; // 0 - lose weight, 1 - maintain weight, 2 - gain muscles,

    private NutritionResult nutrition;

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

    public void storeFoodData(NutritionResult nutrition ){
        this.nutrition = nutrition;

    }

}



