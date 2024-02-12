package main.app.utils;

import java.util.Date;

public class DateFood implements Comparable<DateFood> {
    private Date date;
    private String name;
    private double calories;
    private double servingSize;
    private double totalFat;
    private double saturatedFat;
    private double protein;
    private double sodium;
    private double potassium;
    private double cholesterolMg;
    private double totalCarbs;
    private double fiber;
    private double sugar;
    private boolean dataCulled;

    public DateFood(Date date, String name, double calories, double servingSize, double totalFat, double saturatedFat, double protein, double sodium, double potassium, double cholesterolMg, double totalCarbs, double fiber, double sugar) {
        this.date = date;
        this.name = name;
        this.calories = calories;
        this.servingSize = servingSize;
        this.totalFat = totalFat;
        this.saturatedFat = saturatedFat;
        this.protein = protein;
        this.sodium = sodium;
        this.potassium = potassium;
        this.cholesterolMg = cholesterolMg;
        this.totalCarbs = totalCarbs;
        this.fiber = fiber;
        this.sugar = sugar;
        this.dataCulled = false;
    }

    // Getters and setters for all fields

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    public double getServingSize() {
        return servingSize;
    }

    public double getTotalFat() {
        return totalFat;
    }

    public double getSaturatedFat() {
        return saturatedFat;
    }

    public double getProtein() {
        return protein;
    }

    public double getSodium() {
        return sodium;
    }

    public double getPotassium() {
        return potassium;
    }

    public double getCholesterolMg() {
        return cholesterolMg;
    }

    public double getTotalCarbs() {
        return totalCarbs;
    }

    public double getFiber() {
        return fiber;
    }

    public double getSugar() {
        return sugar;
    }

    public boolean isDataCulled() {
        return dataCulled;
    }

    public void setDataCulled(boolean dataCulled) {
        this.dataCulled = dataCulled;
    }

    @Override
    public String toString() {
        return "Date=" + date + ", name='" + name + '\'' + ", calories=" + calories + ", servingSize=" + servingSize + ", totalFat=" + totalFat + ", saturatedFat=" + saturatedFat + ", protein=" + protein + ", sodium=" + sodium + ", potassium=" + potassium + ", cholesterolMg=" + cholesterolMg + ", totalCarbs=" + totalCarbs + ", fiber=" + fiber + ", sugar=" + sugar + '}';
    }

    @Override
    public int compareTo(DateFood o) {
        return 0;
    }
}
