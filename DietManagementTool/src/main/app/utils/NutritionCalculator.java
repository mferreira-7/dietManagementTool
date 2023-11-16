package main.app.utils;

import main.app.model.NutritionResult;
import main.app.model.Person;

public class NutritionCalculator {

    public static NutritionResult calculateNutrition(Person person) {
        double bmr;

        // Calculating BMR based on gender
        if (person.getGender() == 0) { // male
            bmr = 10 * person.getWeight() + 6.25 * person.getHeight() * 100 - 5 * person.getAge() + 5;
        } else { // female
            bmr = 10 * person.getWeight() + 6.25 * person.getHeight() * 100 - 5 * person.getAge() - 161;
        }

        // Adjusting BMR based on activity level
        double totalCalories = bmr * person.getActivityLevel();

        // Adjustments for goal
        if (person.getGoal() == 0) { // lose weight
            totalCalories *= 0.9;
        } else if (person.getGoal() == 2) { // gain muscles
            totalCalories *= 1.1;
        }
        // if the goal is to maintain weight, no change is needed

        // Assuming a standard distribution of macronutrients: 50% carbs, 30% protein, 20% fat
        double carbs = totalCalories * 0.5 / 4; // 4 calories per gram
        double protein = totalCalories * 0.3 / 4; // 4 calories per gram
        double fat = totalCalories * 0.2 / 9; // 9 calories per gram

        return new NutritionResult(totalCalories, protein, fat, carbs);
    }
}
