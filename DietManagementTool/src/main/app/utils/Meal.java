package main.app.utils;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

enum MealType{
    Breakfast,
    Lunch,
    Dinner,
    NULL
}

public class Meal {
    private LocalDate date;
    private MealType mealType;
    private List<Food> foods;

    public Meal(MealType mealType) {
        this.date = LocalDate.now();
        this.mealType = mealType;
        this.foods = new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public void printFoods() {
        System.out.println("Foods in " + mealType + " on " + date + ":");
        for (Food food : foods) {
            System.out.println(food.getName());
        }
    }

    public Food getTotalNutritionalValues() {
        if (foods.isEmpty()){
            System.out.println("There are no food items in this meal yet");
        }

        printFoods();

        // Initialize total nutritional values
        double totalCalories = 0;
        double totalServingSize = 0;
        double totalTotalFat = 0;
        double totalSaturatedFat = 0;
        double totalProtein = 0;
        double totalSodium = 0;
        double totalPotassium = 0;
        double totalCholesterolMg = 0;
        double totalTotalCarbs = 0;
        double totalFiber = 0;
        double totalSugar = 0;

        // Calculate the sum of nutritional values from all foods
        for (Food food : foods) {
            totalCalories += food.getCalories();
            totalServingSize += food.getServingSize();
            totalTotalFat += food.getTotalFat();
            totalSaturatedFat += food.getSaturatedFat();
            totalProtein += food.getProtein();
            totalSodium += food.getSodium();
            totalPotassium += food.getPotassium();
            totalCholesterolMg += food.getCholesterolMg();
            totalTotalCarbs += food.getTotalCarbs();
            totalFiber += food.getFiber();
            totalSugar += food.getSugar();
        }

        // Create a new Food object with the total nutritional values
        return new Food("Total", totalCalories, totalServingSize, totalTotalFat, totalSaturatedFat,
                totalProtein, totalSodium, totalPotassium, totalCholesterolMg,
                totalTotalCarbs, totalFiber, totalSugar);
    }
}
