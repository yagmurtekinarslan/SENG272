package model;
import java.util.ArrayList;
import java.util.List;

public class Dimension {

    private String name;
    private int coefficient;
    private List<Metric> metrics;

    public Dimension(String name, int coefficient) {
        this.name = name;
        this.coefficient = coefficient;
        this.metrics = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public List<Metric> getMetrics() {
        return metrics;
    }

    public void addMetric(Metric metric) {
        metrics.add(metric);
    }


}
