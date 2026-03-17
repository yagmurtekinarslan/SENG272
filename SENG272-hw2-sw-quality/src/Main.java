import java.util.ArrayList;
import java.util.HashMap;

public class Main{
    public static void main(String[] args){

        HashMap<String, ArrayList<SWSystem>> map= SWSystemData.getAllSystems();

        ArrayList<SWSystem> webList = map.get("Web");

        if (webList == null) {
            System.out.println("Web category not found.");
            return;
        }

        SWSystem ShopSphere=null;

        for (SWSystem s : webList) {
            if (s.getSystem_name().equals("ShopSphere")) {
                ShopSphere = s;
                break;
            }
        }

        if (ShopSphere == null) {
            System.out.println("ShopSphere system not found.");
            return;
        }



        for (QualityDimension qd : ShopSphere.getQualityDim()) {

            if (qd.getDescriptiveName().equals("Functional Suitability")) {
                for (Criterion c : qd.getCriteria()) {
                    if (c.getName().equals("Functional Completeness Ratio")) {
                        c.setMeasuredVal(94);
                    } else if (c.getName().equals("Functional Correctness Ratio")) {
                        c.setMeasuredVal(91);
                    }
                }
            }

            else if (qd.getDescriptiveName().equals("Reliability")) {
                for (Criterion c : qd.getCriteria()) {
                    if (c.getName().equals("Availability Ratio")) {
                        c.setMeasuredVal(99.2);
                    } else if (c.getName().equals("Defect Density")) {
                        c.setMeasuredVal(2.1);
                    }
                }
            }

            else if (qd.getDescriptiveName().equals("Performance Efficiency")) {
                for (Criterion c : qd.getCriteria()) {
                    if (c.getName().equals("Response Time")) {
                        c.setMeasuredVal(220);
                    } else if (c.getName().equals("CPU Utilisation Ratio")) {
                        c.setMeasuredVal(38);
                    }
                }
            }

            else if (qd.getDescriptiveName().equals("Maintainability")) {
                for (Criterion c : qd.getCriteria()) {
                    if (c.getName().equals("Test Coverage Ratio")) {
                        c.setMeasuredVal(72);
                    } else if (c.getName().equals("Cyclomatic Complexity")) {
                        c.setMeasuredVal(8.5);
                    }
                }
            }
        }

        ShopSphere.printReport();

    }
}
