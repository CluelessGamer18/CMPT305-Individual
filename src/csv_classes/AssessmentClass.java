package csv_classes;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        AssessmentClass other = (AssessmentClass) obj;
        return Objects.equals(className, other.className)
                && percentage == other.percentage;
    }

    @Override
    public int hashCode(){
        return Objects.hash(className, percentage);
    }
}
