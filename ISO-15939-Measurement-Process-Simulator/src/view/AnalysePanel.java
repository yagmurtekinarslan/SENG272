package view;

import controller.WizardController;

import javax.swing.*;
import java.awt.*;
import model.Dimension;
import model.Scenario;
import service.ScoreCalculator;

public class AnalysePanel extends StepPanel {

    private JLabel resultLabel;
    private JLabel gapLabel;
    private RadarChartPanel radarChartPanel;

    public AnalysePanel(WizardController controller) {
        super(controller);

        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(25, 50, 25, 50));

        JLabel titleLabel = new JLabel("Measurement Analysis", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));

        JPanel contentPanel = new JPanel(new BorderLayout(20, 20));

        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
        scorePanel.setBorder(BorderFactory.createTitledBorder("Dimension Scores"));

        resultLabel = new JLabel("Analysis results will appear here.");
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        scorePanel.add(resultLabel);

        radarChartPanel = new RadarChartPanel();
        radarChartPanel.setBorder(BorderFactory.createTitledBorder("Radar Chart"));

        JPanel gapPanel = new JPanel(new BorderLayout());
        gapPanel.setBorder(BorderFactory.createTitledBorder("Gap Analysis"));

        gapLabel = new JLabel("Gap analysis will appear here.");
        gapLabel.setFont(new Font("Arial", Font.PLAIN, 15));
        gapPanel.add(gapLabel, BorderLayout.CENTER);

        contentPanel.add(scorePanel, BorderLayout.WEST);
        contentPanel.add(radarChartPanel, BorderLayout.CENTER);
        contentPanel.add(gapPanel, BorderLayout.SOUTH);

        add(titleLabel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
    }

    @Override
    public void loadData() {  // Loads analysis results and gap analysis into the panel

        Scenario scenario = controller.getSessionData().getSelectedScenario();

        if (scenario == null) {
            return;
        }

        StringBuilder resultText = new StringBuilder("<html>");

        for (Dimension dimension : scenario.getDimensions()) {

            double score = ScoreCalculator.calculateDimensionScore(dimension);

            resultText.append(
                    dimension.getName()
                            + " Score: "
                            + String.format("%.2f", score)
                            + "<br><br>"
            );
        }

        resultText.append("</html>");

        resultLabel.setText(resultText.toString());

        Dimension lowestDimension =
                ScoreCalculator.findLowestDimension(scenario);

        double lowestScore =
                ScoreCalculator.calculateDimensionScore(lowestDimension);

        double gap =
                ScoreCalculator.calculateGap(lowestScore);

        String qualityLevel =
                ScoreCalculator.getQualityLevel(lowestScore);

        gapLabel.setText(
                "<html>"
                        + "Lowest Dimension: "
                        + lowestDimension.getName()
                        + "<br>"
                        + "Score: "
                        + String.format("%.2f", lowestScore)
                        + "<br>"
                        + "Gap Value: "
                        + String.format("%.2f", gap)
                        + "<br>"
                        + "Quality Level: "
                        + qualityLevel
                        + "<br><br>"
                        + "This dimension has the lowest score and requires the most improvement."
                        + "</html>"
        );

        radarChartPanel.setDimensions(scenario.getDimensions());
    }

    @Override
    public boolean validateStep() {
        return true;
    }
}
