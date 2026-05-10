package view;

import controller.WizardController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private WizardController controller;
    private PlanPanel planPanel;
    private CollectPanel collectPanel;
    private AnalysePanel analysePanel;
    private StepIndicatorPanel stepIndicatorPanel;

    public MainFrame() {
        controller = new WizardController();
        controller.setMainFrame(this);

        initUI();
    }

    public void initUI() {
        setTitle("ISO 15939 Measurement Process Simulator");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        stepIndicatorPanel = new StepIndicatorPanel();
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        ProfilePanel profilePanel = new ProfilePanel(controller);
        mainPanel.add(profilePanel, "profile");

        DefinePanel definePanel = new DefinePanel(controller);
        mainPanel.add(definePanel, "define");

        planPanel = new PlanPanel(controller);
        mainPanel.add(planPanel, "plan");

        collectPanel = new CollectPanel(controller);
        mainPanel.add(collectPanel, "collect");

        analysePanel = new AnalysePanel(controller);
        mainPanel.add(analysePanel, "analyse");

        setLayout(new BorderLayout());

        add(stepIndicatorPanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        showStep("profile");

        setVisible(true);

    }

    public void showStep(String stepName) {

        if (stepName.equals("plan")) {
            planPanel.loadData();
        }

        if (stepName.equals("collect")) {
            collectPanel.loadData();
        }

        if (stepName.equals("analyse")) {
            analysePanel.loadData();
        }

        if (stepName.equals("profile")) {
            stepIndicatorPanel.setCurrentStep(0);

        } else if (stepName.equals("define")) {
            stepIndicatorPanel.setCurrentStep(1);

        } else if (stepName.equals("plan")) {
            stepIndicatorPanel.setCurrentStep(2);

        } else if (stepName.equals("collect")) {
            stepIndicatorPanel.setCurrentStep(3);

        } else if (stepName.equals("analyse")) {
            stepIndicatorPanel.setCurrentStep(4);
        }

        cardLayout.show(mainPanel, stepName);
    }

}