package main.app.viewmodel;

import main.app.model.Person;
import main.app.model.NutritionResult;
import main.app.utils.NutritionCalculator;

public class NutritionViewModel {
    private Person person;

    public NutritionViewModel(Person person) {
        this.person = person;
    }

    public NutritionResult calculateNutrition() {
        return NutritionCalculator.calculateNutrition(person);
    }
}
