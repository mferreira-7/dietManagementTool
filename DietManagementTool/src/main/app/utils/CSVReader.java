package main.app.utils;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static void GenerateReport(String userName) {
        String csvFile = "src/main/userdata/" + userName + ".csv";
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
                    currentFood = new DateFood(LocalDate.parse(data[0].replaceAll("'", "")), data[1].replaceAll("'", ""), data[2].replaceAll("'", ""), Double.parseDouble(data[3]), Double.parseDouble(data[4]), Double.parseDouble(data[5]), Double.parseDouble(data[6]), Double.parseDouble(data[7]), Double.parseDouble(data[8]), Double.parseDouble(data[9]), Double.parseDouble(data[10]), Double.parseDouble(data[11]), Double.parseDouble(data[12]), Double.parseDouble(data[13]));
                    foods.add(currentFood);
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
