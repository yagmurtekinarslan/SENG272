package view;

import controller.WizardController;

import javax.swing.*;
import java.awt.*;
import model.Dimension;
import model.Metric;
import model.Scenario;

import javax.swing.table.DefaultTableModel;

public class CollectPanel extends StepPanel {

    private JTable dataTable;
    private JButton nextButton;

    public CollectPanel(WizardController controller) {
        super(controller);

        initUI();
    }

    private void initUI() {

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titleLabel = new JLabel("Measurement Collection", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        String[] columns = {
                "Metric",
                "Raw Value"
        };

        Object[][] data = {};

        dataTable = new JTable(data, columns);

        JScrollPane scrollPane = new JScrollPane(dataTable);

        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        nextButton = new JButton("Analyse");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(nextButton);

        add(buttonPanel, BorderLayout.SOUTH);

        nextButton.addActionListener(e -> {
            controller.performAnalysis();
            controller.nextStep();
        });
    }

    @Override
    public void loadData() {

        Scenario scenario = controller.getSessionData().getSelectedScenario();

        String[] columns = {
                "Metric",
                "Raw Value"
        };

        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        if (scenario != null) {

            for (Dimension dimension : scenario.getDimensions()) {

                for (Metric metric : dimension.getMetrics()) {

                    Object[] row = {
                            metric.getName(),
                            metric.getRawValue()
                    };

                    tableModel.addRow(row);
                }
            }
        }

        dataTable.setModel(tableModel);
    }

    @Override
    public boolean validateStep() {
        return true;
    }
}
