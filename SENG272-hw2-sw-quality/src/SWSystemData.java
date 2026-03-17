import java.lang.classfile.attribute.RuntimeInvisibleTypeAnnotationsAttribute;
import java.util.ArrayList;
import java.util.HashMap;

public class SWSystemData {

    public static HashMap<String, ArrayList<SWSystem>> getAllSystems() {

        HashMap<String, ArrayList<SWSystem>> map = new HashMap<>();

        ArrayList<SWSystem> webList = new ArrayList<>();
        webList.add(createECommercePlatform());
        webList.add(createBankingPortal());
        map.put("Web", webList);


        ArrayList<SWSystem> mobileList = new ArrayList<>();
        mobileList.add(createHealthApp());
        map.put("Mobile", mobileList);

        return map;


    }


    private static SWSystem createECommercePlatform() {

        SWSystem s = new SWSystem("ShopSphere", "Web", "3.2.1");

        QualityDimension funcSuit = new QualityDimension("Functional Suitability",
                "QC.FS", 20);

        funcSuit.addCriterion(new Criterion("Functional Completeness Ratio", 50,
                "higher", 0, 100, "%"));

        funcSuit.addCriterion(new Criterion("Functional Correctness Ratio", 50,
                "higher", 0, 100, "%"));

        s.addQualityDim(funcSuit);


        QualityDimension reliability = new QualityDimension("Reliability", "QC.RE",
                20);

        reliability.addCriterion(new Criterion("Availability Ratio", 50,
                "higher", 95, 100, "%"));

        reliability.addCriterion(new Criterion("Defect Density", 50,
                "lower", 0, 20, "defect/KLOC"));

        s.addQualityDim(reliability);


        QualityDimension performanceEfficiency = new QualityDimension("Performance Efficiency",
                "QC.PE", 15);

        performanceEfficiency.addCriterion(new Criterion("Response Time", 40,
                "lower", 0, 500, "ms"));


        performanceEfficiency.addCriterion(new Criterion("CPU Utilisation Ratio", 30,
                "lower", 0, 100, "%"));

        s.addQualityDim(performanceEfficiency);


        QualityDimension maintainability = new QualityDimension("Maintainability", "QC.MA", 15);

        maintainability.addCriterion(new Criterion("Test Coverage Ratio", 50,
                "higher", 0, 100, "%"));

        maintainability.addCriterion(new Criterion("Cyclomatic Complexity", 50,
                "lower", 1, 20, "score"));

        s.addQualityDim(maintainability);

        return s;
    }



    private static SWSystem createBankingPortal() {

        SWSystem s = new SWSystem("BankingPortal", "Web", "2.4.0");

        QualityDimension funcSuit = new QualityDimension("Functional Suitability", "QC.FS", 20);

        funcSuit.addCriterion(new Criterion("Functional Completeness Ratio", 50,
                "higher", 0, 100, "%"));

        funcSuit.addCriterion(new Criterion("Functional Correctness Ratio", 50,
                "higher", 0, 100, "%"));

        s.addQualityDim(funcSuit);



        QualityDimension reliability = new QualityDimension("Reliability", "QC.RE", 20);

        reliability.addCriterion(new Criterion("Availability Ratio", 50,
                "higher", 95, 100, "%"));

        reliability.addCriterion(new Criterion("Defect Density", 50,
                "lower", 0, 20, "defect/KLOC"));

        s.addQualityDim(reliability);



        QualityDimension performanceEfficiency = new QualityDimension(
                "Performance Efficiency", "QC.PE", 15);

        performanceEfficiency.addCriterion(new Criterion("Response Time", 50,
                "lower", 0, 500, "ms"));

        performanceEfficiency.addCriterion(new Criterion("CPU Utilisation Ratio", 50,
                "lower", 0, 100, "%"));

        s.addQualityDim(performanceEfficiency);



        QualityDimension security = new QualityDimension("Security", "QC.SE", 25);

        security.addCriterion(new Criterion("Security Test Coverage", 50,
                "higher", 0, 100, "%"));

        security.addCriterion(new Criterion("Vulnerability Count", 50,
                "lower", 0, 50, "count"));

        s.addQualityDim(security);



        QualityDimension maintainability = new QualityDimension("Maintainability", "QC.MA", 20);

        maintainability.addCriterion(new Criterion("Test Coverage Ratio", 50,
                "higher", 0, 100, "%"));

        maintainability.addCriterion(new Criterion("Cyclomatic Complexity (avg)", 50,
                "lower", 1, 20, "score"));

        s.addQualityDim(maintainability);

        return s;
    }



    private static SWSystem createHealthApp() {

        SWSystem s = new SWSystem("HealthTrack", "Mobile", "1.8.5");

        QualityDimension funcSuit = new QualityDimension("Functional Suitability", "QC.FS", 20);

        funcSuit.addCriterion(new Criterion("Functional Completeness Ratio", 50,
                "higher", 0, 100, "%"));

        funcSuit.addCriterion(new Criterion("Functional Correctness Ratio", 50,
                "higher", 0, 100, "%"));

        s.addQualityDim(funcSuit);



        QualityDimension reliability = new QualityDimension("Reliability", "QC.RE", 20);

        reliability.addCriterion(new Criterion("Availability Ratio", 50,
                "higher", 90, 100, "%"));

        reliability.addCriterion(new Criterion("Defect Density", 50,
                "lower", 0, 20, "defect/KLOC"));

        s.addQualityDim(reliability);



        QualityDimension performanceEfficiency = new QualityDimension("Performance Efficiency", "QC.PE",
                15);

        performanceEfficiency.addCriterion(new Criterion("Response Time", 50,
                "lower", 0, 500, "ms"));

        performanceEfficiency.addCriterion(new Criterion("CPU Utilisation Ratio", 50,
                "lower", 0, 100, "%"));

        s.addQualityDim(performanceEfficiency);



        QualityDimension usability = new QualityDimension("Usability", "QC.US", 15);

        usability.addCriterion(new Criterion("Task Completion Rate", 50,
                "higher", 0, 100, "%"));

        usability.addCriterion(new Criterion("User Error Rate", 50,
                "lower", 0, 100, "%"));

        s.addQualityDim(usability);



        QualityDimension security = new QualityDimension("Security", "QC.SE", 15);

        security.addCriterion(new Criterion("Security Test Coverage", 50,
                "higher", 0, 100, "%"));

        security.addCriterion(new Criterion("Vulnerability Count", 50,
                "lower", 0, 50, "count"));

        s.addQualityDim(security);



        QualityDimension maintainability = new QualityDimension("Maintainability", "QC.MA", 15);

        maintainability.addCriterion(new Criterion("Test Coverage Ratio", 50,
                "higher", 0, 100, "%"));

        maintainability.addCriterion(new Criterion("Cyclomatic Complexity (avg)", 50,
                "lower", 1, 20, "score"));

        s.addQualityDim(maintainability);

        return s;
    }


}


