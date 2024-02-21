package main.app.view;

import main.app.model.NutritionResult;
import main.app.model.Person;
import main.app.viewmodel.NutritionRecommendationViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NutritionRecommendationFrame {
    private NutritionRecommendationViewModel viewModel;
    private String user;

    public NutritionRecommendationFrame(NutritionRecommendationViewModel viewModel, String user) {
        this.viewModel = viewModel;
        this.user = user;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Diet Management Tool - Nutrition Results");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                frame.dispose();
                new MenuFrame(user);
            }
        });

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
/* example usage doesn't work without a username
    public static void main(String[] args) {
        // Example usage
        Person person = new Person(0, 25, 70, 175, 1.5, 0, 1);
        NutritionRecommendationViewModel viewModel = new NutritionRecommendationViewModel(person);
        new NutritionRecommendationFrame(viewModel);
    }*/
}
