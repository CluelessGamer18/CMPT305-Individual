package csv_classes;

import java.util.ArrayList;
import java.util.List;

public class PropertyAssessments {
    private List<PropertyAssessment> assessments;

    public PropertyAssessments(List<PropertyAssessment> assessments){
        this.assessments = assessments;
    }

    public void addAssessment(PropertyAssessment assessment){
        assessments.add(assessment);
    }

    public List<PropertyAssessment> getAssessments(){
        return new ArrayList<>(assessments);
    }
}
