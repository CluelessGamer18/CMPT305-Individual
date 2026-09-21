package csv_classes;

public class AssessmentClass {
    private String className;
    private int percentage;

    public AssessmentClass(String className, int percentage){
        this.className = className;
        this.percentage = percentage;
    }

    public String getClassName(){
        return className;
    }

    public int getPercentage(){
        return percentage;
    }

    @Override
    public String toString(){
        return className + " " + percentage + "%";
    }
}
