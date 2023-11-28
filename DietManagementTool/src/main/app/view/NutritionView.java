package main.app.view;

import javax.swing.*;
import main.app.model.NutritionResult;
import main.app.model.Person;
import main.app.viewmodel.NutritionViewModel;
import java.awt.*;

public class NutritionView {
    private NutritionViewModel viewModel;

    public NutritionView(NutritionViewModel viewModel) {
        this.viewModel = viewModel;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Diet Management Tool - Nutrition Results");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        NutritionResult result = viewModel.calculateNutrition();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("Nutrition Calories Result Based on User Input:");
        styleLabel(titleLabel, true);
        panel.add(titleLabel);

        panel.add(createResultLabel("Total Calories: " + (int) result.getTotalCalories()));
        panel.add(createResultLabel("Protein: " + (int) result.getProtein() + "g"));
        panel.add(createResultLabel("Fat: " + (int) result.getFat() + "g"));
        panel.add(createResultLabel("Carbs: " + (int) result.getCarbs() + "g"));

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JLabel createResultLabel(String text) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        styleLabel(label, false);
        return label;
    }

    private void styleLabel(JLabel label, boolean isTitle) {
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (isTitle) {
            label.setFont(new Font("Roboto", Font.BOLD, 20));
        } else {
            label.setFont(new Font("Roboto", Font.PLAIN, 18));
        }
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
    }

    public static void main(String[] args) {
        // Example usage
        Person person = new Person(0, 25, 70, 175, 1.5, 0, 1);
        NutritionViewModel viewModel = new NutritionViewModel(person);
        new NutritionView(viewModel);
    }
}
