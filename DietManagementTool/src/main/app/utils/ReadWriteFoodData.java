package main.app.utils;

import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

public class ReadWriteFoodData {

    String username;
    String dataPath;
    boolean initialFileSearch;
    int lineNum = 0;

    /*

    Assuming this naming convention

    date, mealType, name, calories, servingSize, totalFat, saturatedFat, protein, sodium, potassium, cholesterolMg, totalCarbs, fiber, sugar

    */


    /*Constructor*/
    public ReadWriteFoodData(String username) {
        this.username = username;//Hopefully the username is the same name as the object's name
        this.dataPath = "src/main/userdata/" + username + ".csv";

        if (new File(dataPath).isFile()) {
            this.initialFileSearch = true;
        }

    }

    public static void main(String[] args) {
        ReadWriteFoodData goob = new ReadWriteFoodData("Rachapol");
        /*
        Meal example = new Meal(MealType.Breakfast);

        Food sausage = FoodItemSearchAPI.getNutritionInfo("Sausage");
        Food eggs = FoodItemSearchAPI.getNutritionInfo("eggs");
        Food toast = FoodItemSearchAPI.getNutritionInfo("toast");

        example.addFood(sausage);
        example.addFood(eggs);
        example.addFood(toast);

        goob.createAndWriteToCSV(goob.mealToString(example));

        Meal second = new Meal(MealType.Lunch);

        Food chicken = FoodItemSearchAPI.getNutritionInfo("200g chicken");
        Food fries = FoodItemSearchAPI.getNutritionInfo("150g fries");
        Food ketchup = FoodItemSearchAPI.getNutritionInfo("12g ketchup");

        second.addFood(chicken);
        second.addFood(fries);
        second.addFood(ketchup);

        goob.createAndWriteToCSV(goob.mealToString(second));*/

        Vector<Meal> readIn = goob.readIntoMeals();

        for (Meal meals : readIn) {
            meals.printFoods();
        }

    }

    public void createAndWriteToCSV(String dataToWrite) {
        /*Creates a file to write in*/
        try {
            File creator = new File(dataPath);
            if (creator.createNewFile()) {
                System.out.println("File created: " + creator.getName());//creates a file and writes the data to it
                writeProtected(dataToWrite);
            } else {
                appendProtected(dataToWrite);//If a file already exists with that name, do above but append not overwrite
                System.out.println("Appending to file " + dataPath);
            }
        } catch (IOException e) {
            System.out.println("An error occurred creating a file.");
            e.printStackTrace();
        }
    }

    private void writeProtected(String dataToWrite) {
        try {
            FileWriter writer = new FileWriter(dataPath);
            writer.write("date,mealType,name,calories,servingSize,totalFat,saturatedFat,protein,sodium,potassium,cholesterolMg,totalCarbs,fiber,sugar\n");
            writer.write(dataToWrite);
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred trying to write.");
            e.printStackTrace();
        }
    }

    private void appendProtected(String dataToWrite) {
        String line;
        StringBuilder appender = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(dataPath))) {
            while ((line = br.readLine()) != null)//loops for every line
            {
                appender.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter writer = new FileWriter(dataPath);
            writer.write(appender.toString());
            writer.write(dataToWrite);
            writer.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred trying to write.");
            e.printStackTrace();
        }
    }

    public Vector<Meal> readIntoMeals() {
        //UtilVars
        Vector<Meal> readMeals = new Vector<>();
        Meal proxyMeal = new Meal(MealType.NULL);
        Food proxyFood;
        String line;
        MealType currentType = MealType.NULL, newType;
        LocalDate currentDate = LocalDate.now(), newDate;

        try (BufferedReader br = new BufferedReader(new FileReader(dataPath))) {
            boolean firstLineSkipped = false; //flag to track if the first line has been skipped
            boolean firstMealtoProcess = true;

            while ((line = br.readLine()) != null)//loops for every line
            {
                if (!firstLineSkipped) {
                    firstLineSkipped = true;
                    continue; // Skip processing the first line
                }
                String[] data = line.split(",");
                data[0] = data[0].replaceAll("'", "");
                data[1] = data[1].replaceAll("'", "");
                data[2] = data[2].replaceAll("'", "");


                //Checks if it is the first meal in the file
                if (firstMealtoProcess) {
                    currentDate = LocalDate.parse(data[0]);
                    currentType = MealType.valueOf(data[1]);

                    proxyMeal.clearOverwriteDateAndType(currentDate, currentType);
                    firstMealtoProcess = false;
                }

                //Meal Processing Section
                newType = MealType.valueOf(data[1]);
                newDate = LocalDate.parse(data[0]);


                if (currentType == newType && currentDate.equals(newDate)) {
                    proxyFood = new Food(data[2], Double.parseDouble(data[3]),
                            Double.parseDouble(data[4]), Double.parseDouble(data[5]),
                            Double.parseDouble(data[6]), Double.parseDouble(data[7]),
                            Double.parseDouble(data[8]), Double.parseDouble(data[9]),
                            Double.parseDouble(data[10]), Double.parseDouble(data[11]),
                            Double.parseDouble(data[12]), Double.parseDouble(data[13]));
                    proxyMeal.addFood(proxyFood);
                } else {
                    readMeals.add(proxyMeal);

                    if (data.length > 0) {
                        proxyMeal = new Meal(MealType.NULL);
                        proxyMeal.clearOverwriteDateAndType(newDate, newType);
                        currentType = newType;
                        currentDate = newDate;

                        proxyFood = new Food(data[2], Double.parseDouble(data[3]),
                                Double.parseDouble(data[4]), Double.parseDouble(data[5]),
                                Double.parseDouble(data[6]), Double.parseDouble(data[7]),
                                Double.parseDouble(data[8]), Double.parseDouble(data[9]),
                                Double.parseDouble(data[10]), Double.parseDouble(data[11]),
                                Double.parseDouble(data[12]), Double.parseDouble(data[13]));

                        proxyMeal.addFood(proxyFood);
                    }
                }
            }
            readMeals.add(proxyMeal);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (readMeals.isEmpty()) {
            //Returns a null meal if there is nothing to read or add
            readMeals.add(new Meal(MealType.NULL));

            return readMeals;
        }

        return readMeals;
    }

    public String mealToString(Meal meal) {
        //Util Vars
        Meal inputMeal = meal;
        /*List of all the foods in the meal*/
        List<Food> foodList = meal.getFoods();

        MealType mealType = meal.getMealType();

        /*Getting the dates*/
        LocalDate date = meal.getDate();
        String dateString = date.toString();

        StringBuilder str = new StringBuilder();

        for (Food food : foodList) {

            str.append("'" + dateString + "','");
            str.append(mealType + "',");
            str.append(food.toSaveString());//.toSaveString already includes a newline character

        }

        return str.toString();
    }
}
