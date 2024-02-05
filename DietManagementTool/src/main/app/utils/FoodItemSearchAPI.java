package main.app.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;
import java.net.URLEncoder;

enum foodDataType{
    NAME,
    CALORIES,
    SERVING_SIZE,
    TOTAL_FAT,
    SATURATED_FAT,
    PROTEIN,
    SODIUM,
    POTASSIUM,
    CHOLESTEROL_MG,
    TOTAL_CARBS,
    FIBER,
    SUGAR

}


public class FoodItemSearchAPI {
    //This function only works by searching 1 item at a time
    public static Food getNutritionInfo(String apiKey, String foodName) {
        Vector<String> results = new Vector<>();

        try {
            // Create the URL for the Calorieninjas API
            //THIS IS NEEDED FOR SEARCHING ITEMS WITH MULTIPLE WORDS
            String query = URLEncoder.encode(foodName, "UTF-8");

            URL url = new URL("https://api.calorieninjas.com/v1/nutrition?query=" + query);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method and headers
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("X-Api-Key", apiKey);

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Check if the request was successful (status code 200)
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line, adaptedLine;

                while ((line = reader.readLine()) != null) {
                    // Process the response
                    if (line.startsWith("{\"items\": [{\"name\":")){
                        adaptedLine = line.replaceAll("\"", "");
                        adaptedLine = adaptedLine.replaceAll("}", "");
                        adaptedLine = adaptedLine.replaceAll("\\{", "");
                        //System.out.println(adaptedLine);

                        adaptedLine = adaptedLine.substring(8, adaptedLine.length() - 3);
                        //System.out.println(adaptedLine);

                        String[] dataArr = adaptedLine.split(", ");

                        for (String i:dataArr){
                            results.add(i.toString());//If two results of food data come, only the first will be saved
                        }

                    } else if (line.startsWith("{\"items\": [{")) {
                        System.out.println("No food with that name can be found");
                    }
                }

                reader.close();


            } else {
                results.add("API Request failed with response code: " + responseCode);
            }

            connection.disconnect();

        } catch (IOException e) {
            results.add("Error: " + e.getMessage());
        }

        // Create a Food object with the extracted data
        return new Food(
                getName(results),
                getStat(foodDataType.CALORIES, results),
                getStat(foodDataType.SERVING_SIZE, results),
                getStat(foodDataType.TOTAL_FAT, results),
                getStat(foodDataType.SATURATED_FAT, results),
                getStat(foodDataType.PROTEIN, results),
                getStat(foodDataType.SODIUM, results),
                getStat(foodDataType.POTASSIUM, results),
                getStat(foodDataType.CHOLESTEROL_MG, results),
                getStat(foodDataType.TOTAL_CARBS, results),
                getStat(foodDataType.FIBER, results),
                getStat(foodDataType.SUGAR, results)
        );
    }



    public static Double getStat(foodDataType TYPE, Vector<String> foodData){
        switch (TYPE){
            case CALORIES -> {
                return extractNumericalValue(foodData.get(1));
            }
            case SERVING_SIZE -> {
                return extractNumericalValue(foodData.get(2));
            }
            case TOTAL_FAT -> {
                return extractNumericalValue(foodData.get(3));
            }
            case SATURATED_FAT -> {
                return extractNumericalValue(foodData.get(4));
            }
            case PROTEIN -> {
                return extractNumericalValue(foodData.get(5));
            }
            case SODIUM -> {
                return extractNumericalValue(foodData.get(6));
            }
            case POTASSIUM -> {
                return extractNumericalValue(foodData.get(7));
            }
            case CHOLESTEROL_MG -> {
                return extractNumericalValue(foodData.get(8));
            }
            case TOTAL_CARBS -> {
                return extractNumericalValue(foodData.get(9));
            }
            case FIBER -> {
                return extractNumericalValue(foodData.get(10));
            }
            case SUGAR -> {
                return extractNumericalValue(foodData.get(11));
            }
        }
        return Double.NaN;
    }

    // Function to extract numerical value from a string
    private static double extractNumericalValue(String input) {
        // Split the input string by ":"
        String[] parts = input.split(":");

        // Ensure there are two parts (name and value)
        if (parts.length == 2) {
            // Extract the numerical value as a double
            try {
                return Double.parseDouble(parts[1].trim());
            } catch (NumberFormatException e) {
                // Handle parsing error
                System.err.println("Error parsing numerical value: " + e.getMessage());
            }
        }

        // Return a default value or handle the error as needed
        return 0.0;
    }

    public static String getName(Vector<String> foodData){

        String data = foodData.get(0);

        // Split the input string by ":"
        String[] parts = data.split(":");

        if (parts.length == 2) {
            try {
                return parts[1].trim();
            } catch (NumberFormatException e) {
                // Handle parsing error
                System.err.println("Error parsing string value: " + e.getMessage());
            }
        }
        return "NULL";
    }


    private static void printStringVector(Vector<String> vector) {
        System.out.print("[");
        for (int i = 0; i < vector.size(); i++) {
            System.out.print(vector.get(i));
            if (i < vector.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        String apiKey = "yA0vx21q3TVlRNuMK6ZXdQ==9UmAidTa6j7VpLtR";
        String foodName = "chicken breast and salad";

        // Call the function to get nutrition information
        Food nutritionInfo = getNutritionInfo(apiKey, foodName);

        /*    OUTDATED
        // Display the results
        for (String result : nutritionInfo) {
            System.out.println(result);
        }

        //System.out.println(getStat(foodDataType.PROTEIN,nutritionInfo) + " " + getName(nutritionInfo) + " " + getStat(foodDataType.SUGAR,nutritionInfo));
        */

        System.out.println(nutritionInfo + " " + nutritionInfo.getSugar());

        Meal example = new Meal(MealType.Breakfast);

        Food sausage = getNutritionInfo(apiKey, "Sausage");
        Food eggs = getNutritionInfo(apiKey, "eggs");
        Food toast = getNutritionInfo(apiKey, "toast");

        example.addFood(sausage);
        example.addFood(eggs);
        example.addFood(toast);

        example.printFoods();

        Food totalNutritionalValues = example.getTotalNutritionalValues();

        System.out.println(totalNutritionalValues);

    }
}
