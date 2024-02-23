package main.app.model;

public class NutritionResult {
    private double totalCalories;
    private double protein;
    private double totalFat;
    private double carbs;
    private double sodium;
    private double sugar;
    private double fiber;
    private double saturatedFat;
    private double potassium;
    private double cholesterol;

    public NutritionResult(double totalCalories, double protein, double totalFat, double carbs, double sodium, double sugar, double fiber, double saturatedFat, double potassium, double cholesterol) {
        this.totalCalories = totalCalories;
        this.protein = protein;
        this.totalFat = totalFat;
        this.carbs = carbs;
        this.sodium = sodium;
        this.sugar = sugar;
        this.fiber = fiber;
        this.saturatedFat = saturatedFat;
        this.potassium = potassium;
        this.cholesterol = cholesterol;
    }

    // Getter methods for the calculated values
    public double getTotalCalories() {
        return totalCalories;
    }

    public double getProtein() {
        return protein;
    }

    public double getFat() {
        return totalFat;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getSodium() {
        return sodium;
    }

    public double getSugar(){return sugar;}

    public double getFiber(){return fiber;}

    public double getSaturatedFat(){return saturatedFat;}

    public double getPotassium(){return potassium;}

    public double getCholesterol(){return cholesterol;}


}