package main.app.model;

public class NutritionResult {
    private double totalCalories;
    private double protein;
    private double fat;
    private double carbs;

    public NutritionResult(double totalCalories, double protein, double fat, double carbs) {
        this.totalCalories = totalCalories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    // Getter methods for the calculated values
    public double getTotalCalories() {
        return totalCalories;
    }

    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return fat;
    }

    public double getCarbs() {
        return carbs;
    }
}
