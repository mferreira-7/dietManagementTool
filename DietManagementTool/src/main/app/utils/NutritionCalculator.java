package main.app.utils;

import main.app.model.NutritionResult;
import main.app.model.Person;


public class NutritionCalculator {

    public static <string> NutritionResult calculateNutrition(Person person) {
        double bmr;

        // Calculating BMR based on gender
        if (person.getGender() == 0) { // male
            bmr = (10 * person.getWeight()) + (6.25 * person.getHeight()) - (5 * person.getAge() + 5);
        } else { // female
            bmr = (10 * person.getWeight()) + (6.25 * person.getHeight()) - (5 * person.getAge() - 161);
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
        double proteinPercentage = 0.1;
        double totalFatPercentage = 0.2;

        double sodiumPercantage = 0.0015;
        double sugarPercantage = 0.1;
        double fiberPercantage = 0.03;
        double saturatedFatPercantage= 0.05;
        double potassiumPercantage = 0.0025;
        double cholesterolPercantage = 0.06;

        if (person.getDietaryPreference() == 1) { // vegan
            proteinPercentage -= 0.05;
            carbsPercentage += 0.05;
            potassiumPercantage += 0.005;

        } else if (person.getDietaryPreference() == 2) { // vegetarian
            proteinPercentage -= 0.03;
            carbsPercentage += 0.03;
            potassiumPercantage += 0.005;

        }

        if (person.getGoal() == 0) { // lose weight
            totalFatPercentage -= 0.05;
            proteinPercentage += 0.05;
            sugarPercantage -= 0.05;
            fiberPercantage -= 0.005;
            saturatedFatPercantage -= 0.01;
        } else if (person.getGoal() == 2) { // gain muscles
            proteinPercentage += 0.1;
            carbsPercentage -= 0.05;
            fiberPercantage += 0.01;
            potassiumPercantage += 0.01;
        }

        double carbs = totalCalories * carbsPercentage / 4; // 4 calories per gram
        double protein = totalCalories * proteinPercentage / 4; // 4 calories per gram
        double totalFat = totalCalories * totalFatPercentage / 9; // 9 calories per gram
        double sodium = totalCalories * sodiumPercantage; //due to salt has no calories, this is the amount of the person should take per day
        double sugar = totalCalories * sugarPercantage / 4; // 4 calories per gram
        double fiber = totalCalories * fiberPercantage / 2; // 2 calories per gram
        double saturatedFat = totalCalories * saturatedFatPercantage / 9; // 9 calories per gram
        double potassium = totalCalories * potassiumPercantage; //potassium has no calories
        double cholesterol = totalCalories * cholesterolPercantage; //cholesterol han no calories

        return new NutritionResult(totalCalories, protein, totalFat, carbs, sodium, sugar, fiber, saturatedFat, potassium, cholesterol);
    }
}