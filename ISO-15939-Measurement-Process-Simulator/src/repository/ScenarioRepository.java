package repository;

import model.Mode;
import model.Scenario;

import java.util.ArrayList;
import java.util.List;

public class ScenarioRepository {

    private List<Scenario> scenarios;

    public ScenarioRepository() {
        scenarios = new ArrayList<>();
        loadScenarios();
    }

    public void loadScenarios() {

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