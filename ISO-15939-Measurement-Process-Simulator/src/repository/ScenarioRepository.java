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

    public void loadScenarios() {

        Scenario scenarioC = new Scenario(
                "Scenario C - Team Alpha",
                QualityType.PRODUCT,
                Mode.EDUCATION
        );

        Dimension usability = new Dimension("Usability", 25);

        Metric susScore = new Metric(
                "SUS Score",
                50,
                Direction.HIGHER_IS_BETTER,
                0,
                100,
                "points"
        );
        susScore.setRawValue(89);

        Metric onboardingTime = new Metric(
                "Onboarding Time",
                50,
                Direction.LOWER_IS_BETTER,
                0,
                60,
                "min"
        );
        onboardingTime.setRawValue(5);

        usability.addMetric(susScore);
        usability.addMetric(onboardingTime);

        scenarioC.addDimension(usability);

        scenarios.add(scenarioC);

    }

    public List<Scenario> getScenariosByMode(Mode mode) {
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