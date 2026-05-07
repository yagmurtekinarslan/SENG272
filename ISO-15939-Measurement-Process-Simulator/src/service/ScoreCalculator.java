package service;

import model.Direction;
import model.Dimension;
import model.Metric;
import model.Scenario;

public class ScoreCalculator {

    public static double roundToNearestHalf(double value) {
        return Math.round(value * 2) / 2.0;
    }

    public static double calculateMetricScore(Metric metric) {
        double min = metric.getMinValue();
        double max = metric.getMaxValue();
        double value = metric.getRawValue();

        double score;

        if (metric.getDirection() == Direction.HIGHER_IS_BETTER) {
            score = 1 + ((value - min) / (max - min)) * 4;
        } else {
            score = 5 - ((value - min) / (max - min)) * 4;
        }

        score = Math.max(1.0, Math.min(5.0, score));

        return roundToNearestHalf(score);
    }

    public static double calculateDimensionScore(Dimension dimension) {
        double totalWeightedScore = 0;
        double totalCoefficient = 0;

        for (Metric metric : dimension.getMetrics()) {
            totalWeightedScore += metric.getScore() * metric.getCoefficient();
            totalCoefficient += metric.getCoefficient();
        }

        if (totalCoefficient == 0) {
            return 0;
        }

        return totalWeightedScore / totalCoefficient;
    }

    public static Dimension findLowestDimension(Scenario scenario) {
        Dimension lowestDimension = null;
        double lowestScore = Double.MAX_VALUE;

        for (Dimension dimension : scenario.getDimensions()) {
            double score = calculateDimensionScore(dimension);

            if (score < lowestScore) {
                lowestScore = score;
                lowestDimension = dimension;
            }
        }

        return lowestDimension;
    }

    public static double calculateGap(double score) {
        return 5.0 - score;
    }

    public static String getQualityLevel(double score) {
        if (score >= 4.5) {
            return "Excellent";
        } else if (score >= 3.5) {
            return "Good";
        } else if (score >= 2.5) {
            return "Needs Improvement";
        } else {
            return "Poor";
        }
    }
}