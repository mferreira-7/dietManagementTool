package main.app.viewmodel;

import main.app.model.Person;

public class UserInfoViewModel {
    private int age;
    private int gender; // 0 - male, 1 - female
    private double weight; // in kilograms
    private double height; // in meters
    private double activityLevel; // 1.2 - low, 1.5 - moderate, 1.8 - high
    private int dietaryPreference; // 0 - neither, 1 - vegan, 2 - vegetarian
    private int goal; // 0 - lose weight, 1 - maintain weight, 2 - gain muscles

    // Existing methods...

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setActivityLevel(double activityLevel) {
        this.activityLevel = activityLevel;
    }

    public void setDietaryPreference(int dietaryPreference) {
        this.dietaryPreference = dietaryPreference;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public Person createPerson() {
        // Creating a Person instance using the set information
        return new Person(gender, age, weight, height, activityLevel, dietaryPreference, goal);
    }
}
