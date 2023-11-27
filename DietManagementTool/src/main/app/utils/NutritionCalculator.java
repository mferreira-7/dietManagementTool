package main.app.utils;

import main.app.model.NutritionResult;
import main.app.model.Person;

public class NutritionCalculator {

    public static NutritionResult calculateNutrition(Person person) {
        double bmr;

        // Calculating BMR based on gender
        if (person.getGender() == 0) { // male
            bmr = 10 * person.getWeight() + 6.25 * person.getHeight() - 5 * person.getAge() + 5;
        } else { // female
            bmr = 10 * person.getWeight() + 6.25 * person.getHeight() - 5 * person.getAge() - 161;
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

        // Adjusting macronutrients based on dietary preferences and goals
        double carbsPercentage = 0.5;
        double proteinPercentage = 0.3;
        double fatPercentage = 0.2;

        if (person.getDietaryPreference() == 1) { // vegan
            proteinPercentage -= 0.05;
            carbsPercentage += 0.05;
        } else if (person.getDietaryPreference() == 2) { // vegetarian
            proteinPercentage -= 0.03;
            carbsPercentage += 0.03;
        }

        if (person.getGoal() == 0) { // lose weight
            fatPercentage -= 0.05;
            proteinPercentage += 0.05;
        } else if (person.getGoal() == 2) { // gain muscles
            proteinPercentage += 0.1;
            carbsPercentage -= 0.1;
        }

        double carbs = totalCalories * carbsPercentage / 4; // 4 calories per gram
        double protein = totalCalories * proteinPercentage / 4; // 4 calories per gram
        double fat = totalCalories * fatPercentage / 9; // 9 calories per gram

        return new NutritionResult(totalCalories, protein, fat, carbs);
    }
}
