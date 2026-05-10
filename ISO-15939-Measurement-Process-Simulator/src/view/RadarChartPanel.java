package view;

import model.Dimension;
import service.ScoreCalculator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RadarChartPanel extends JPanel {

    private List<Dimension> dimensions;

    public RadarChartPanel() {
        setPreferredSize(new java.awt.Dimension(450, 350));
        setBackground(Color.WHITE);
    }

    public void setDimensions(List<Dimension> dimensions) {
        this.dimensions = dimensions;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {  // Draws the radar chart using Graphics2D
        super.paintComponent(g);

        if (dimensions == null || dimensions.isEmpty()) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g;

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(getWidth(), getHeight()) / 3;

        int count = dimensions.size();

        int[] xPoints = new int[count];
        int[] yPoints = new int[count];

        for (int i = 0; i < count; i++) {
            double angle = 2 * Math.PI * i / count - Math.PI / 2;

            int axisX = centerX + (int) (Math.cos(angle) * radius);
            int axisY = centerY + (int) (Math.sin(angle) * radius);

            g2.drawLine(centerX, centerY, axisX, axisY);

            double score = ScoreCalculator.calculateDimensionScore(dimensions.get(i));
            double ratio = score / 5.0;

            xPoints[i] = centerX + (int) (Math.cos(angle) * radius * ratio);
            yPoints[i] = centerY + (int) (Math.sin(angle) * radius * ratio);

            g2.drawString(dimensions.get(i).getName(), axisX, axisY);
        }

        g2.drawPolygon(xPoints, yPoints, count);
    }
}