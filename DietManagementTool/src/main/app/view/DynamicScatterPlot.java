package main.app.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DynamicScatterPlot extends JPanel {
    private List<Point> dataPoints;

    public DynamicScatterPlot() {
        dataPoints = new ArrayList<>();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Dynamic Scatter Plot");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            DynamicScatterPlot scatterPlot = new DynamicScatterPlot();
            frame.add(scatterPlot);
            frame.setSize(600, 600);
            frame.setVisible(true);

            // Example of adding data points dynamically
            scatterPlot.addDataPoint(50, 100);
            scatterPlot.addDataPoint(100, 200);
            scatterPlot.addDataPoint(150, 50);
        });
    }

    public void addDataPoint(int x, int y) {
        dataPoints.add(new Point(x, y));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set up the axes
        int padding = 100;
        int width = getWidth();
        int height = getHeight();
        g2d.drawLine(padding, padding, padding, height - padding);
        g2d.drawLine(padding, height - padding, width - padding, height - padding);

        g2d.drawString("Time (X Axis)", width - padding + 5, height - padding + 5);
        g2d.drawString("Nutrient (Y Axis)", padding - 15, padding - 5);


        // Draw data points
        for (Point point : dataPoints) {
            int x = padding + point.x;
            int y = height - padding - point.y;
            g2d.setColor(Color.BLUE);
            g2d.fillOval(x - 3, y - 3, 6, 6);
        }
    }
}
