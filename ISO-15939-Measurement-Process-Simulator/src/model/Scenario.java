package model;

import java.util.ArrayList;
import java.util.List;

public class Scenario {

    private String name;
    private QualityType qualityType;
    private Mode mode;
    private List<Dimension> dimensions;

    public Scenario(String name, QualityType qualityType, Mode mode) {
        this.name = name;
        this.qualityType = qualityType;
        this.mode = mode;
        this.dimensions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public QualityType getQualityType() {
        return qualityType;
    }


    public Mode getMode() {
        return mode;
    }

    public List<Dimension> getDimensions() {
        return dimensions;
    }

    public void addDimension(Dimension dimension) {
        dimensions.add(dimension);
    }

    public List<Metric> getAllMetrics() {
        List<Metric> allMetrics = new ArrayList<>();
        for (Dimension dimension : dimensions) {
            allMetrics.addAll(dimension.getMetrics());
        }
        return allMetrics;
    }

}
