public class Criterion {

    private String name;
    private int weight;
    private String direction;
    private double minVal;
    private double maxVal;
    private String unit;
    private double measuredVal;

    public Criterion(String name, int weight,String direction, double minVal, double maxVal, String unit){
        this.name=name;
        this.weight=weight;
        this.direction=direction;
        this.minVal=minVal;
        this.maxVal=maxVal;
        this.unit=unit;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }


    public String getDirection(){
        return direction;
    }

    public double getMinVal(){
        return minVal;
    }

    public double getMaxVal(){
        return maxVal;
    }

    public String getUnit() {
        return unit;
    }

    public double getMeasuredVal() {
        return measuredVal;
    }

    public void setMeasuredVal(double measuredVal){
         this.measuredVal=measuredVal;

    }

    public double calculateScore(){

        double score;

        if (maxVal == minVal) {
            return 1.0;
        }

        if(direction.equals("higher")){
            score= 1+(((measuredVal-minVal) / (maxVal-minVal))*4);
        }

        else{
            score= 5-(((measuredVal-minVal) / (maxVal-minVal))*4);
        }

        if (score < 1.0) {
            score = 1.0;
        } else if (score > 5.0) {
            score = 5.0;
        }

        score = Math.round(score * 2) / 2.0;

        return score;
    }

}
