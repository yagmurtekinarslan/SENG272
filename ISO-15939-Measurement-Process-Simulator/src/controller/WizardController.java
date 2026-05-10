package controller;

import model.Mode;
import model.QualityType;
import model.SessionData;
import repository.ScenarioRepository;
import service.ScoreCalculator;
import view.MainFrame;
import model.UserProfile;
import model.Scenario;

public class WizardController {

    private SessionData sessionData;
    private ScenarioRepository repository;
    private ScoreCalculator calculator;
    private MainFrame mainFrame;

    public WizardController() {
        sessionData = new SessionData();
        repository = new ScenarioRepository();
        calculator = new ScoreCalculator();
    }


    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }


    public SessionData getSessionData() {
        return sessionData;
    }


    private int currentStep = 0;

    public void nextStep() {  // Handles wizard step transitions
        currentStep++;

        if (currentStep == 1) {
            mainFrame.showStep("define");

        } else if (currentStep == 2) {
            mainFrame.showStep("plan");

        } else if (currentStep == 3) {
            mainFrame.showStep("collect");
        }
        else if (currentStep == 4) {
            mainFrame.showStep("analyse");
        }
    }



    public void saveProfile(String username, String school, String sessionName) {
        UserProfile profile = new UserProfile(username, school, sessionName);
        sessionData.setProfile(profile);
    }


    public void saveDefinition(QualityType type, Mode mode, String scenarioName) {
        sessionData.setSelectedQualityType(type);
        sessionData.setSelectedMode(mode);

        Scenario selectedScenario = repository.findScenario(scenarioName);
        sessionData.setSelectedScenario(selectedScenario);
    }


    public void performAnalysis() {  // Calculates metric scores before analysis step

        model.Scenario scenario =
                sessionData.getSelectedScenario();

        if (scenario == null) {
            return;
        }

        for (model.Dimension dimension : scenario.getDimensions()) {

            for (model.Metric metric : dimension.getMetrics()) {

                double score =
                        ScoreCalculator.calculateMetricScore(metric);

                metric.setScore(score);
            }
        }
    }


    public java.util.List<model.Scenario> getScenariosByMode(Mode mode) {
        return repository.getScenariosByMode(mode);
    }

}