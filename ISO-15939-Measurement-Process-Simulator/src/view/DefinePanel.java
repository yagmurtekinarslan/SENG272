package view;

import controller.WizardController;

import javax.swing.*;
import java.awt.*;
import model.Mode;
import model.Scenario;

public class DefinePanel extends StepPanel {

    private JRadioButton productRadio;
    private JRadioButton processRadio;

    private JRadioButton educationRadio;
    private JRadioButton healthRadio;

    private JComboBox<String> scenarioComboBox;
    private JButton nextButton;

    public DefinePanel(WizardController controller) {
        super(controller);

        initUI();
    }

    private void initUI() {

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));

        JLabel titleLabel = new JLabel("Define Measurement Scope", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JPanel qualityPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        qualityPanel.setBorder(BorderFactory.createTitledBorder("Quality Type"));

        productRadio = new JRadioButton("Product");
        processRadio = new JRadioButton("Process");

        ButtonGroup qualityGroup = new ButtonGroup();
        qualityGroup.add(productRadio);
        qualityGroup.add(processRadio);

        qualityPanel.add(productRadio);
        qualityPanel.add(processRadio);

        JPanel modePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        modePanel.setBorder(BorderFactory.createTitledBorder("Mode"));

        educationRadio = new JRadioButton("Education");
        healthRadio = new JRadioButton("Health");

        ButtonGroup modeGroup = new ButtonGroup();
        modeGroup.add(educationRadio);
        modeGroup.add(healthRadio);

        modePanel.add(educationRadio);
        modePanel.add(healthRadio);

        educationRadio.addActionListener(e -> loadScenarios(Mode.EDUCATION));
        healthRadio.addActionListener(e -> loadScenarios(Mode.HEALTH));

        JPanel scenarioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        scenarioPanel.setBorder(BorderFactory.createTitledBorder("Scenario"));

        scenarioComboBox = new JComboBox<>();

        scenarioPanel.add(scenarioComboBox);

        centerPanel.add(qualityPanel);
        centerPanel.add(modePanel);
        centerPanel.add(scenarioPanel);

        add(titleLabel, BorderLayout.NORTH);

        nextButton = new JButton("Next");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(nextButton);

        add(buttonPanel, BorderLayout.SOUTH);

        add(centerPanel, BorderLayout.CENTER);

        nextButton.addActionListener(e -> {

            if (validateStep()) {

                controller.saveDefinition(
                        productRadio.isSelected()
                                ? model.QualityType.PRODUCT
                                : model.QualityType.PROCESS,

                        educationRadio.isSelected()
                                ? model.Mode.EDUCATION
                                : model.Mode.HEALTH,

                        scenarioComboBox.getSelectedItem().toString()
                );

                controller.nextStep();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Please complete all selections.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });
    }

    @Override
    public void loadData() {

    }

    @Override
    public boolean validateStep() {

        return (productRadio.isSelected() || processRadio.isSelected())
                && (educationRadio.isSelected() || healthRadio.isSelected())
                && scenarioComboBox.getSelectedItem() != null;
    }

    private void loadScenarios(Mode mode) {
        scenarioComboBox.removeAllItems();

        for (Scenario scenario : controller.getScenariosByMode(mode)) {
            scenarioComboBox.addItem(scenario.getName());
        }
    }


}