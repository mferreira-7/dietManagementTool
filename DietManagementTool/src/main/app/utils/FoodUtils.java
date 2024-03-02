package main.app.utils;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FoodUtils {

    public static Map<LocalDate, Double> calculateDailyCalories(List<DateFood> foods) {
        Map<LocalDate, Double> dailyCalories = new HashMap<>();

        for (DateFood food : foods) {
            LocalDate date = food.getDate();
            double calories = food.getCalories();
            dailyCalories.merge(date, calories, Double::sum);
        }

        // Convert HashMap to TreeMap to ensure the dates are sorted
        return new TreeMap<>(dailyCalories);
    }
}
