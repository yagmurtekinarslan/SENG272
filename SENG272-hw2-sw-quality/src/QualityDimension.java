import java.util.ArrayList;

public class QualityDimension {

    private String descriptive_name;
    private String ISO_code;
    private int weight;
    private ArrayList<Criterion> criteria;

    public QualityDimension(String descriptive_name, String ISO_code, int weight){
        this.descriptive_name=descriptive_name;
        this.ISO_code=ISO_code;
        this.weight=weight;
        this.criteria = new ArrayList<>();
    }

    public String getDescriptiveName() {
        return descriptive_name;
    }

    public String getIsoCode() {
        return ISO_code;
    }

    public int getWeight() {
        return weight;
    }

    public ArrayList<Criterion> getCriteria() {
        return criteria;
    }

    public void addCriterion(Criterion c){

     criteria.add(c);

    }


    public double calculateDimScore(){

        double total=0;
        double totalWeight=0;

        for(Criterion c : criteria){

            total += c.calculateScore()*c.getWeight();
            totalWeight+=c.getWeight();

        }

        if(totalWeight==0){
            return 0;
        }

        return total/totalWeight;

    }

    public String getQualityLabel(){
         double score= calculateDimScore();

        if(score>=4.5){
            return "Excellent Quality";
        }
        else if(score>=3.5){
            return "Good Quality";
        }
        else if (score>=2.5){
            return "Needs Improvement";
        }
        else {
            return "Poor Quality";
        }

    }

  public double getQualityGap(){

        return 5.0-calculateDimScore();
  }

}
