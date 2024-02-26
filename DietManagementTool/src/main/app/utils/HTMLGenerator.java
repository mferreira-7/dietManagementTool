package main.app.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HTMLGenerator {

    public static void generateHTML(List<DateFood> foods) {
        // HTML template
        String htmlContent = "<!DOCTYPE html>\n" + "<html>\n" + "<head>\n" + "<title>DateFood List</title>\n" + "<style>\n" + "table {\n" + "    font-family: Arial, sans-serif;\n" + "    border-collapse: collapse;\n" + "    width: 100%;\n" + "}\n" + "th, td {\n" + "    border: 1px solid #dddddd;\n" + "    text-align: left;\n" + "    padding: 8px;\n" + "}\n" + "th {\n" + "    background-color: #f2f2f2;\n" + "}\n" + "</style>\n" + "</head>\n" + "<body>\n" + "<table>\n" + "<tr>\n" + "<th>Date</th>\n" + "<th>Meal Type</th>\n" + // New column for mealType
                "<th>Name</th>\n" + "<th>Calories</th>\n" + "<th>Serving Size</th>\n" + "<th>Total Fat</th>\n" + "<th>Saturated Fat</th>\n" + "<th>Protein</th>\n" + "<th>Sodium</th>\n" + "<th>Potassium</th>\n" + "<th>Cholesterol Mg</th>\n" + "<th>Total Carbs</th>\n" + "<th>Fiber</th>\n" + "<th>Sugar</th>\n" + "</tr>\n";

        // Fill HTML table with data
        for (DateFood food : foods) {
            htmlContent += "<tr>\n" + "<td>" + food.getDate() + "</td>\n" + "<td>" + food.getMealType() + "</td>\n" + // Add mealType data
                    "<td>" + food.getName() + "</td>\n" + "<td>" + food.getCalories() + "</td>\n" + "<td>" + food.getServingSize() + "</td>\n" + "<td>" + food.getTotalFat() + "</td>\n" + "<td>" + food.getSaturatedFat() + "</td>\n" + "<td>" + food.getProtein() + "</td>\n" + "<td>" + food.getSodium() + "</td>\n" + "<td>" + food.getPotassium() + "</td>\n" + "<td>" + food.getCholesterolMg() + "</td>\n" + "<td>" + food.getTotalCarbs() + "</td>\n" + "<td>" + food.getFiber() + "</td>\n" + "<td>" + food.getSugar() + "</td>\n" + "</tr>\n";
        }

        htmlContent += "</table>\n" + "</body>\n" + "</html>";

        // Write HTML content to a file
        try (FileWriter fileWriter = new FileWriter("DateFoodList.html")) {
            fileWriter.write(htmlContent);
            System.out.println("HTML file created successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Assuming 'foods' is the list of DateFood objects obtained from CSVReader
        // CSVReader csvReader = new CSVReader();
        // List<DateFood> foods = csvReader.readCSV("your_csv_file_path.csv");
        List<DateFood> foods = new ArrayList<>(); // Sample list of DateFood objects

        // Call generateHTML method
        generateHTML(foods);
    }
}
