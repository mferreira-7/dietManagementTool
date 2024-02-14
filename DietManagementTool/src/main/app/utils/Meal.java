package main.app.utils;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;


public class Meal {
    private LocalDate date;
    private MealType mealType;
    private List<Food> foods;
    private String mealName;

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

    public void setDate(LocalDate date){this.date = date;}

    public void setMealType(MealType newMealType){mealType = newMealType;}

    public void addFood(Food food) {
        foods.add(food);
    }

    public void printFoods() {
        System.out.println("Foods in " + mealType + " on " + date + ":");
        for (Food food : foods) {
            System.out.println(food.getName());
        }
    }

    public List<Food> getFoods(){
        return foods;
    }

    public String displayFoods() {
        String displayString = "";
        for (Food food : foods) {
            displayString = displayString + ", \n" + food.getName();
        }
        return displayString;
    }

    public Food getTotalNutritionalValues() {
        if (foods.isEmpty()){
            System.out.println("There are no food items in this meal yet");
        }

        printFoods();

        //Initializing total nutritional values for the food calculashionz
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

        //Calculating the sum of nutritional values from all foods
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

        //Creating a new Food object with the total nutritional values
        return new Food("Total", totalCalories, totalServingSize, totalTotalFat, totalSaturatedFat,
                totalProtein, totalSodium, totalPotassium, totalCholesterolMg,
                totalTotalCarbs, totalFiber, totalSugar);
    }

    public void clearOverwriteDateAndType(LocalDate date, MealType type){
        this.date = date; this.mealType = type;
        foods.clear();
    }

    public void changeMealName(String name){ mealName = name;}

    public String getMealName(){return mealName;}

    //I get this is somewhat not needed but this is just for convenience.
    public boolean isMealNull(){
        if (mealType == MealType.NULL){
            return true;
        }
        else return false;
    }

}
