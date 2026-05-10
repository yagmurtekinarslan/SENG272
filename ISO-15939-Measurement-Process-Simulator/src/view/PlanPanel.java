package view;

import controller.WizardController;

import javax.swing.*;
import java.awt.*;
import model.Dimension;
import model.Metric;
import model.Scenario;
import javax.swing.table.DefaultTableModel;

public class PlanPanel extends StepPanel {

    private JTable metricsTable;
    private JButton nextButton;

    public PlanPanel(WizardController controller) {
        super(controller);

        initUI();
    }

    private void initUI() {

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titleLabel = new JLabel("Measurement Planning", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        String[] columns = {
                "Dimension",
                "Metric",
                "Coefficient",
                "Direction",
                "Range",
                "Unit"
        };

        Object[][] data = {};

        metricsTable = new JTable(data, columns);

        JScrollPane scrollPane = new JScrollPane(metricsTable);

        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        nextButton = new JButton("Next");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(nextButton);

        add(buttonPanel, BorderLayout.SOUTH);

        nextButton.addActionListener(e -> {
            controller.nextStep();
        });
    }

    @Override
    public void loadData() {
        Scenario scenario = controller.getSessionData().getSelectedScenario();

        String[] columns = {
                "Dimension",
                "Metric",
                "Coefficient",
                "Direction",
                "Range",
                "Unit"
        };

        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        if (scenario != null) {
            for (Dimension dimension : scenario.getDimensions()) {
                for (Metric metric : dimension.getMetrics()) {
                    Object[] row = {
                            dimension.getName(),
                            metric.getName(),
                            metric.getCoefficient(),
                            metric.getDirection(),
                            metric.getRangeText(),
                            metric.getUnit()
                    };

                    tableModel.addRow(row);
                }
            }
        }

        metricsTable.setModel(tableModel);
    }

    @Override
    public boolean validateStep() {
        return true;
    }
}