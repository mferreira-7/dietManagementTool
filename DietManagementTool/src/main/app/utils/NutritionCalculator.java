package main.app.utils;

import main.app.model.NutritionResult;
import main.app.model.Person;

public class NutritionCalculator {

    private static final int CALORIES_PER_GRAM_PROTEIN = 4;
    private static final int CALORIES_PER_GRAM_FAT = 9;
    private static final int CALORIES_PER_GRAM_CARBS = 4;

    private static final double BASE_METABOLIC_RATE_MALE = 88.362;
    private static final double BASE_METABOLIC_RATE_FEMALE = 447.593;
    private static final double AGE_FACTOR = 13.397;
    private static final double WEIGHT_FACTOR = 4.799;
    private static final double HEIGHT_FACTOR = 5.677;

    private static final double VEGAN_FACTOR = 1.1;
    private static final double VEGETARIAN_FACTOR = 1.05;

    public static NutritionResult calculateNutrition(Person person) {
        double bmr;

        // Calculate BMR based on gender
        if (person.getGender() == 0) { // Male
            bmr = BASE_METABOLIC_RATE_MALE + (WEIGHT_FACTOR * person.getWeight()) + (HEIGHT_FACTOR * person.getHeight()) - (AGE_FACTOR * person.getAge());
        } else { // Female
            bmr = BASE_METABOLIC_RATE_FEMALE + (WEIGHT_FACTOR * person.getWeight()) + (HEIGHT_FACTOR * person.getHeight()) - (AGE_FACTOR * person.getAge());
        }

        // Adjust BMR based on activity level
        double totalCalories = bmr * person.getActivityLevel();

        // Adjust calories based on goal
        switch (person.getGoal()) {
            case 0: // Lose weight
                totalCalories *= 0.8;
                break;
            case 2: // Gain muscles
                totalCalories *= 1.2;
                break;
            // case 1: Maintain weight (no adjustment needed)
        }

        // Adjust calories based on dietary preference
        if (person.getDietaryPreference() == 1) { // Vegan
            totalCalories *= VEGAN_FACTOR;
        } else if (person.getDietaryPreference() == 2) { // Vegetarian
            totalCalories *= VEGETARIAN_FACTOR;
        }

        // Calculate macronutrients
        double protein = person.getWeight() * 2.2; // Recommended protein intake in grams per kilogram of body weight
        double fat = totalCalories * 0.25 / CALORIES_PER_GRAM_FAT;
        double carbs = (totalCalories - (protein * CALORIES_PER_GRAM_PROTEIN) - (fat * CALORIES_PER_GRAM_FAT)) / CALORIES_PER_GRAM_CARBS;

        return new NutritionResult(totalCalories, protein, fat, carbs);
    }
}
