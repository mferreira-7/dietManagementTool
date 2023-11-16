package main.app.view;

import javax.swing.*;
import main.app.model.NutritionResult;
import main.app.model.Person;
import main.app.viewmodel.NutritionViewModel;

public class NutritionView {
    private NutritionViewModel viewModel;

    public NutritionView(NutritionViewModel viewModel) {
        this.viewModel = viewModel;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Diet Management App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        NutritionResult result = viewModel.calculateNutrition();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Total Calories: " + result.getTotalCalories()));
        panel.add(new JLabel("Protein: " + result.getProtein() + "g"));
        panel.add(new JLabel("Fat: " + result.getFat() + "g"));
        panel.add(new JLabel("Carbs: " + result.getCarbs() + "g"));

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Example usage
        Person person = new Person(0, 25, 70, 1.75, 1.5, 0, 1);
        NutritionViewModel viewModel = new NutritionViewModel(person);
        new NutritionView(viewModel);
    }
}
