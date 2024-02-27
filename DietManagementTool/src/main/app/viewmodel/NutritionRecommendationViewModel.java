package main.app.viewmodel;

import main.app.model.NutritionResult;
import main.app.model.Person;
import main.app.utils.NutritionCalculator;

public class NutritionRecommendationViewModel {
    private Person person;

    public NutritionRecommendationViewModel(Person person) {
        this.person = person;
    }

    public NutritionResult calculateNutrition() {
        return NutritionCalculator.calculateNutrition(person);
    }
}
