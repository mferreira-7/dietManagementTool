package main.app.utils;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CSVReader {
    public static void GenerateReport(String userName) {
        String csvFile = "src/main/users/" + userName + ".csv";
        String line;
        String csvSplitBy = ",";

        List<DateFood> foods = new ArrayList<>();
        DateFood currentFood = null;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            boolean firstLineSkipped = false; // Add a flag to track if the first line has been skipped
            while ((line = br.readLine()) != null) {
                if (!firstLineSkipped) {
                    firstLineSkipped = true;
                    continue; // Skip processing the first line
                }
                String[] data = line.split(csvSplitBy);
                if (currentFood == null || currentFood.isDataCulled()) {
                    try {
                        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(data[0]);
                        currentFood = new DateFood(date, data[1].replaceAll("'", ""), // name
                                Double.parseDouble(data[2]), // calories
                                Double.parseDouble(data[3]), // servingSize
                                Double.parseDouble(data[4]), // totalFat
                                Double.parseDouble(data[5]), // saturatedFat
                                Double.parseDouble(data[6]), // protein
                                Double.parseDouble(data[7]), // sodium
                                Double.parseDouble(data[8]), // potassium
                                Double.parseDouble(data[9]), // cholesterolMg
                                Double.parseDouble(data[10]), // totalCarbs
                                Double.parseDouble(data[11]), // fiber
                                Double.parseDouble(data[12]) // sugar
                        );
                        foods.add(currentFood);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Handle cases where data needs to be appended to the current food item
                    // Example: multiple lines for the same food item
                }


                currentFood.setDataCulled(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print out the foods for verification
        for (DateFood food : foods) {
            System.out.println(food);
        }

        HTMLGenerator.generateHTML(foods);
    }
/*  Example main doesn't work without the user
    public static void main(String[] args) {
        GenerateReport("vlad123");
    }*/
}
