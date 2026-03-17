import java.util.ArrayList;
import java.util.SimpleTimeZone;

public class SWSystem {
    private String system_name;
    private String category;
    private String software_version;
    private ArrayList<QualityDimension> qualityDim;

    public SWSystem(String system_name,String category, String software_version){
        this.system_name=system_name;
        this.category=category;
        this.software_version=software_version;
        this.qualityDim=new ArrayList<>();

    }

    public String getSystem_name(){
        return system_name;
    }

    public String getCategory(){
        return category;
    }

    public String getSoftware_version(){
        return software_version;
    }

    public ArrayList<QualityDimension> getQualityDim(){
        return qualityDim;
    }

    public void addQualityDim(QualityDimension qd){

        qualityDim.add(qd);
    }

    public double calculateOverall_Quality_Score(){
        double OQS=0;
        double totalWeight=0;

        for (QualityDimension qd : qualityDim){
            OQS +=qd.calculateDimScore()*qd.getWeight();
            totalWeight+=qd.getWeight();
        }
        if (totalWeight == 0) {
            return 0;
        }
        return OQS/totalWeight;
    }

    public QualityDimension findWeakestDimension(){
        if(qualityDim.isEmpty()){
            return null;
        }
        QualityDimension weakest = qualityDim.get(0);

        for (QualityDimension qd : qualityDim) {
            if (qd.calculateDimScore() < weakest.calculateDimScore()) {
                weakest = qd;
            }
        }

        return weakest;
    }

    public String getOverallQualityLabel() {
        double score = calculateOverall_Quality_Score();

        if (score >= 4.5) {
            return "Excellent Quality";
        } else if (score >= 3.5) {
            return "Good Quality";
        } else if (score >= 2.5) {
            return "Needs Improvement";
        } else {
            return "Poor Quality";
        }
    }


    public void printReport() {
        System.out.println("========================================");
        System.out.println(" SOFTWARE QUALITY EVALUATION REPORT (ISO/IEC 25010)");
        System.out.println("System: " + system_name + " v" + software_version + " (" + category + ")");
        System.out.println("========================================");
        System.out.println();

        for (QualityDimension qd : qualityDim) {
            System.out.println("--- " + qd.getDescriptiveName() + " [" + qd.getIsoCode() + "] (Weight: " + qd.getWeight() + ") ---");

            for (Criterion c : qd.getCriteria()) {
                String directionText;

                if (c.getDirection().equalsIgnoreCase("higher")) {
                    directionText = "Higher is better";
                } else {
                    directionText = "Lower is better";
                }

                System.out.printf("%s: %.1f %s -> Score: %.1f (%s)%n",
                        c.getName(),
                        c.getMeasuredVal(),
                        c.getUnit(),
                        c.calculateScore(),
                        directionText);
            }

            System.out.printf(">> Dimension Score: %.1f/5 [%s]%n",
                    qd.calculateDimScore(),
                    qd.getQualityLabel());
            System.out.println();
        }

        System.out.println("========================================");
        System.out.printf("OVERALL QUALITY SCORE: %.1f/5 [%s]%n",
                calculateOverall_Quality_Score(),
                getOverallQualityLabel());
        System.out.println("========================================");
        System.out.println();

        QualityDimension weakest = findWeakestDimension();
        if (weakest != null) {
            System.out.println("GAP ANALYSIS (ISO/IEC 25010)");
            System.out.println("========================================");
            System.out.println("Weakest Characteristic : " + weakest.getDescriptiveName() + " [" + weakest.getIsoCode() + "]");
            System.out.printf("Score: %.1f/5 | Gap: %.1f%n",
                    weakest.calculateDimScore(),
                    weakest.getQualityGap());
            System.out.println("Level: " + weakest.getQualityLabel());
            System.out.println(">> This characteristic requires the most improvement.");
            System.out.println("========================================");
        }
    }
}




