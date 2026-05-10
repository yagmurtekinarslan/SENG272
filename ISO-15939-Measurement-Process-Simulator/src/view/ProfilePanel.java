package view;

import controller.WizardController;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends StepPanel {

    private JTextField usernameField;
    private JTextField schoolField;
    private JTextField sessionField;

    private JButton nextButton;

    public ProfilePanel(WizardController controller) {
        super(controller);

        initUI();
    }

    private void initUI() {

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));

        JLabel titleLabel = new JLabel("User Profile", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));

        JPanel formPanel = new JPanel(new GridBagLayout());

        formPanel.setBorder(BorderFactory.createEmptyBorder(80, 0, 80, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        usernameField = new JTextField(30);
        usernameField.setPreferredSize(new Dimension(320, 32));
        formPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("School Name:"), gbc);

        gbc.gridx = 1;
        schoolField = new JTextField(30);
        schoolField.setPreferredSize(new Dimension(320, 32));
        formPanel.add(schoolField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Session Name:"), gbc);

        gbc.gridx = 1;
        sessionField = new JTextField(30);
        sessionField.setPreferredSize(new Dimension(320, 32));
        formPanel.add(sessionField, gbc);

        nextButton = new JButton("Next");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(nextButton);

        nextButton.addActionListener(e -> {
            if (validateStep()) {
                controller.saveProfile(
                        usernameField.getText(),
                        schoolField.getText(),
                        sessionField.getText()
                );

                controller.nextStep();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "All fields must be filled in before proceeding to the next step.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        add(titleLabel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        usernameField.addActionListener(e -> schoolField.requestFocus());
        schoolField.addActionListener(e -> sessionField.requestFocus());
        sessionField.addActionListener(e -> nextButton.doClick());
    }

    @Override
    public void loadData() {

    }

    @Override
    public boolean validateStep() {

        return !usernameField.getText().trim().isEmpty()
                && !schoolField.getText().trim().isEmpty()
                && !sessionField.getText().trim().isEmpty();
    }
}