package view;

import controller.WizardController;

import javax.swing.*;

public abstract class StepPanel extends JPanel {

    protected WizardController controller;

    public StepPanel(WizardController controller) {
        this.controller = controller;
    }

    public abstract void loadData();

    public abstract boolean validateStep();
}