package repository;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class ScenarioRepository {

    private List<Scenario> scenarios;

    public ScenarioRepository() {
        scenarios = new ArrayList<>();
        loadScenarios();
    }


    private Metric createMetric(String name, int coefficient, Direction direction,
                                double minValue, double maxValue, String unit, double rawValue) {
        Metric metric = new Metric(name, coefficient, direction, minValue, maxValue, unit);
        metric.setRawValue(rawValue);
        return metric;
    }

    public void loadScenarios() { // Adds all hard-coded scenarios to the repository
        addEducationScenarioC();
        addEducationScenarioD();
        addHealthScenarioA();
        addHealthScenarioB();
    }

    private void addEducationScenarioC() {
        Scenario scenarioC = new Scenario(
                "Scenario C - Team Alpha",
                QualityType.PRODUCT,
                Mode.EDUCATION
        );

        Dimension usability = new Dimension("Usability", 25);
        usability.addMetric(createMetric("SUS Score", 50, Direction.HIGHER_IS_BETTER, 0, 100, "points", 89));
        usability.addMetric(createMetric("Onboarding Time", 50, Direction.LOWER_IS_BETTER, 0, 60, "min", 5));

        Dimension performance = new Dimension("Performance Efficiency", 20);
        performance.addMetric(createMetric("Video Start Time", 50, Direction.LOWER_IS_BETTER, 0, 15, "sec", 4));
        performance.addMetric(createMetric("Concurrent Exams", 50, Direction.HIGHER_IS_BETTER, 0, 600, "users", 480));

        Dimension accessibility = new Dimension("Accessibility", 20);
        accessibility.addMetric(createMetric("WCAG Compliance", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 88));
        accessibility.addMetric(createMetric("Screen Reader Score", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 82));

        Dimension reliability = new Dimension("Reliability", 20);
        reliability.addMetric(createMetric("Uptime", 50, Direction.HIGHER_IS_BETTER, 95, 100, "%", 98));
        reliability.addMetric(createMetric("MTTR", 50, Direction.LOWER_IS_BETTER, 0, 120, "min", 35));

        Dimension functionalSuitability = new Dimension("Functional Suitability", 15);
        functionalSuitability.addMetric(createMetric("Feature Completion", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 90));
        functionalSuitability.addMetric(createMetric("Assignment Submit Rate", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 86));

        scenarioC.addDimension(usability);
        scenarioC.addDimension(performance);
        scenarioC.addDimension(accessibility);
        scenarioC.addDimension(reliability);
        scenarioC.addDimension(functionalSuitability);

        scenarios.add(scenarioC);
    }

    private void addEducationScenarioD() {
        Scenario scenarioD = new Scenario(
                "Scenario D - Team Beta",
                QualityType.PRODUCT,
                Mode.EDUCATION
        );

        Dimension usability = new Dimension("Usability", 25);
        usability.addMetric(createMetric("SUS Score", 50, Direction.HIGHER_IS_BETTER, 0, 100, "points", 72));
        usability.addMetric(createMetric("Onboarding Time", 50, Direction.LOWER_IS_BETTER, 0, 60, "min", 18));

        Dimension reliability = new Dimension("Reliability", 25);
        reliability.addMetric(createMetric("Uptime", 50, Direction.HIGHER_IS_BETTER, 95, 100, "%", 96));
        reliability.addMetric(createMetric("MTTR", 50, Direction.LOWER_IS_BETTER, 0, 120, "min", 65));

        scenarioD.addDimension(usability);
        scenarioD.addDimension(reliability);

        scenarios.add(scenarioD);
    }

    private void addHealthScenarioA() {
        Scenario scenarioA = new Scenario(
                "Scenario A - Hospital System",
                QualityType.PRODUCT,
                Mode.HEALTH
        );

        Dimension performance = new Dimension("Performance Efficiency", 30);
        performance.addMetric(createMetric("Patient Record Load Time", 50, Direction.LOWER_IS_BETTER, 0, 10, "sec", 3));
        performance.addMetric(createMetric("Concurrent Users", 50, Direction.HIGHER_IS_BETTER, 0, 500, "users", 350));

        Dimension reliability = new Dimension("Reliability", 30);
        reliability.addMetric(createMetric("System Uptime", 50, Direction.HIGHER_IS_BETTER, 95, 100, "%", 99));
        reliability.addMetric(createMetric("Incident Recovery Time", 50, Direction.LOWER_IS_BETTER, 0, 120, "min", 25));

        scenarioA.addDimension(performance);
        scenarioA.addDimension(reliability);

        scenarios.add(scenarioA);
    }

    private void addHealthScenarioB() {
        Scenario scenarioB = new Scenario(
                "Scenario B - Clinic System",
                QualityType.PRODUCT,
                Mode.HEALTH
        );

        Dimension security = new Dimension("Security", 30);
        security.addMetric(createMetric("Login Failure Rate", 50, Direction.LOWER_IS_BETTER, 0, 100, "%", 12));
        security.addMetric(createMetric("Access Control Score", 50, Direction.HIGHER_IS_BETTER, 0, 100, "%", 85));

        Dimension usability = new Dimension("Usability", 25);
        usability.addMetric(createMetric("User Satisfaction Score", 50, Direction.HIGHER_IS_BETTER, 0, 100, "points", 78));
        usability.addMetric(createMetric("Average Task Completion Time", 50, Direction.LOWER_IS_BETTER, 0, 60, "min", 14));

        scenarioB.addDimension(security);
        scenarioB.addDimension(usability);

        scenarios.add(scenarioB);
    }



    public List<Scenario> getScenariosByMode(Mode mode) {  // Returns scenarios that belong to the selected mode
        List<Scenario> filteredScenarios = new ArrayList<>();

        for (Scenario scenario : scenarios) {
            if (scenario.getMode() == mode) {
                filteredScenarios.add(scenario);
            }
        }

        return filteredScenarios;
    }

    public Scenario findScenario(String name) {
        for (Scenario scenario : scenarios) {
            if (scenario.getName().equals(name)) {
                return scenario;
            }
        }

        return null;
    }
}