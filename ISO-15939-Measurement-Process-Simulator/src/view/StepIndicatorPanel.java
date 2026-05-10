package view;

import javax.swing.*;
import java.awt.*;

public class StepIndicatorPanel extends JPanel {

    private JLabel[] stepLabels;

    private final String[] steps = {
            "Profile",
            "Define",
            "Plan",
            "Collect",
            "Analyse"
    };

    public StepIndicatorPanel() {

        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        stepLabels = new JLabel[steps.length];

        for (int i = 0; i < steps.length; i++) {

            stepLabels[i] = new JLabel(steps[i]);

            stepLabels[i].setFont(new Font("Arial", Font.BOLD, 14));

            add(stepLabels[i]);
        }

        setCurrentStep(0);
    }

    public void setCurrentStep(int step) {

        for (int i = 0; i < stepLabels.length; i++) {

            if (i == step) {

                stepLabels[i].setForeground(Color.BLUE);

            } else if (i < step) {

                stepLabels[i].setForeground(new Color(0, 128, 0));

            } else {

                stepLabels[i].setForeground(Color.GRAY);
            }
        }
    }
}