package main.app.view;

import main.app.utils.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;


import static main.app.utils.Constants.SCREEN_HEIGHT;
import static main.app.utils.Constants.SCREEN_WIDTH;

public class DailyFrame extends JFrame {

    InputPanel inputPanel;
    ControlPanel controlPanel;
    MealDisplayPanel mealDisplayPanel;
    Meal mealToAdd = new Meal(MealType.Other);
    String username;
    private JButton backButton;

    public DailyFrame(String username) {
        initializeComponents();
        setLayout();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(700, 500);
        this.username = username;

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                setVisible(false);
                dispose();
                new MenuFrame(username);
            }
        });
    }


    private void initializeComponents() {
        setSize(700, 500);
        setResizable(false);
        setTitle("Diet Management Tool - Daily Food Intake");


        backButton = new JButton("Back"); // Initialize the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Dispose of the DailyFrame
                new MenuFrame(username).setVisible(true); // Open the MenuFrame
            }
        });
        applyStyles();

    }



    private void applyStyles() {
        backButton.setFont(new Font("Arial", Font.BOLD, 18));
        backButton.setFocusPainted(false);

        // Style the backButton button
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
    }

    private void setLayout() {

        setResizable(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 50, 10, 50); // Top, left, bottom, right padding
//        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);

        // Set the main layout to BorderLayout
        getContentPane().setLayout(new BorderLayout());

        // Assuming SCREEN_HEIGHT and SCREEN_WIDTH are defined and represent the size of the screen
        int topPanelHeight = (int) (SCREEN_HEIGHT * 0.10); // Calculate 10% of the screen height for the top panel

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Create the top panel with a fixed height
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setMinimumSize(new Dimension(screenSize.width, topPanelHeight));
        topPanel.setMaximumSize(new Dimension(screenSize.width, topPanelHeight));
        topPanel.setPreferredSize(new Dimension(screenSize.width, topPanelHeight));

        // Add a logo to the top panel
        JLabel logoLabel = new JLabel(); // Create a label to hold the logo
        logoLabel.setHorizontalAlignment(JLabel.CENTER); // Set the logo to align center
        URL logoUrl = getClass().getResource("/main/resources/Images/Image_5.png");
        ImageIcon logoIcon = new ImageIcon(logoUrl);
        Image logoImage = logoIcon.getImage();
        // Scale the Logo to fit the application window or a specific size
        Image scaledLogo = logoImage.getScaledInstance(-1,topPanelHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledLogoIcon = new ImageIcon(scaledLogo);
        logoLabel.setIcon(scaledLogoIcon); // Add the logo to the label
        topPanel.add(logoLabel, BorderLayout.CENTER); // Add the label to the top panel

        getContentPane().add(topPanel, BorderLayout.NORTH); // Top panel at the top


        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));


        inputPanel = new InputPanel();
        inputPanel.setBackground(Color.lightGray);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(inputPanel, gbc);

        mealDisplayPanel = new MealDisplayPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(mealDisplayPanel, gbc);

        controlPanel = new ControlPanel(this, inputPanel, mealDisplayPanel);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(controlPanel, gbc);

        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.add(backButton);
        getContentPane().add(backButtonPanel);
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(backButtonPanel, gbc);

        revalidate();
        repaint();
        setMealType();

        setLocationRelativeTo(null);
    }

    public void setMealType() {
        mealToAdd.setMealType(inputPanel.getMealType());
    }

    public String foodList() {
        return mealToAdd.displayFoods();
    }

    public boolean addFoodToMeal() {
        boolean successFlag = true;
        Food foodToAdd = FoodItemSearchAPI.getNutritionInfo(inputPanel.getUserSearch());

        if (foodToAdd.getName() == "NULL") {
            return false;
        }

        mealToAdd.addFood(foodToAdd);
        inputPanel.clearInputField();

        return successFlag;
    }

    public void saveMeal() {

        if (mealDisplayPanel.noFoodCheck()) {
            JOptionPane.showMessageDialog(null,
                    "No foods have been added. ",
                    "Notice - No items",
                    JOptionPane.WARNING_MESSAGE);
        } else {

            ReadWriteFoodData writer = new ReadWriteFoodData(username);
            writer.createAndWriteToCSV(writer.mealToString(mealToAdd));
            dispose();
            new MenuFrame(username).setVisible(true); // Open the MenuFrame

        }
    }
    /*
    public static void main(String[] args) {
        new DailyFrame("vlad123");
    }*/
}
