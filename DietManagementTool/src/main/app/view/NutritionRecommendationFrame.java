package main.app.view;

import main.app.model.NutritionResult;
import main.app.viewmodel.NutritionRecommendationViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NutritionRecommendationFrame {
    private final String username;
    private final NutritionRecommendationViewModel viewModel;

    public NutritionRecommendationFrame(NutritionRecommendationViewModel viewModel, String username) {
        this.viewModel = viewModel;
        this.username = username;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Diet Management Tool - Nutrition Results");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        NutritionResult result = viewModel.calculateNutrition();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Nutrition Calories Result Based on User Input:");
        styleLabel(titleLabel, true);
        panel.add(titleLabel);

        panel.add(createResultLabel("Total Calories: " + (int) result.getTotalCalories()));
        panel.add(createResultLabel("Protein: " + (int) result.getProtein() + "g"));
        panel.add(createResultLabel("Total Fat: " + (int) result.getFat() + "g"));
        panel.add(createResultLabel("Carbs: " + (int) result.getCarbs() + "g"));
        panel.add(createResultLabel("Sodium: " + (int) result.getSodium() + "g"));
        panel.add(createResultLabel("Sugar: " + (int) result.getSugar() + "g"));
        panel.add(createResultLabel("Fiber: " + (int) result.getFiber() + "g"));
        panel.add(createResultLabel("Saturated Fat: " + (int) result.getSaturatedFat() + "g"));
        panel.add(createResultLabel("Potassium: " + (int) result.getPotassium() + "g"));
        panel.add(createResultLabel("Cholesterol: " + (int) result.getCholesterol() + "mg"));

        // Adding a back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MenuFrame menuFrame = new MenuFrame(username);
                menuFrame.setVisible(true);
            }
        });
        panel.add(backButton);

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setSize(600, 600);
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
            label.setFont(new Font("Roboto", Font.BOLD, 24));
            label.setForeground(Color.BLUE); // Set title color
        } else {
            label.setFont(new Font("Roboto", Font.PLAIN, 18));
            label.setForeground(Color.BLACK); // Set text color

        }
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    }
}