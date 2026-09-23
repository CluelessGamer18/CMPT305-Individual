package csv_classes;

import java.util.ArrayList;
import java.util.List;

public class PropertyAssessments {
    private List<PropertyAssessment> assessments;

    public PropertyAssessments(){
        this.assessments = new ArrayList<>();
    }

    public static PropertyAssessments buildAssessments(String[][] data){
        PropertyAssessments result  = new PropertyAssessments();
        for (String[] row: data) {
            int accountNumber = Integer.parseInt(row[0]);
            int suite = parseIntOrDefault(row[1], 0);
            int houseNumber = parseIntOrDefault(row[2], 0);
            String streetName = row[3];
            int neighbourhoodId = parseIntOrDefault(row[4], 0);
            String neighbouhoodName = row[5];
            String ward = row[6];
            int assessedValue = Integer.parseInt(row[7]);
            String taxClass = row[8];
            boolean garage = row[9].trim().equalsIgnoreCase("Y");
            List<AssessmentClass> classes = new ArrayList<>();
            double latitude = Double.parseDouble(row[16]);
            double longitude = Double.parseDouble(row[17]);

            for (int i=0; i<3; i++){
                int index = 10 + i;
                String name = row[index].trim();
                if (!name.isEmpty()) {
                    classes.add(new AssessmentClass(row[index], Integer.parseInt(row[index+3])));
                }
            }

            Neighbourhood neighbourhood = new Neighbourhood(neighbourhoodId, neighbouhoodName, ward);
            Address address = new Address(suite, houseNumber, streetName);
            Location location = new Location(latitude, longitude);

            result.addAssessment(new PropertyAssessment(accountNumber, assessedValue, garage, taxClass, address, neighbourhood, classes, location));

        }
        return result;

    }

    public void addAssessment(PropertyAssessment assessment){
        assessments.add(assessment);
    }

    public List<PropertyAssessment> getAssessments(){
        return new ArrayList<>(assessments);
    }

    /**
     * A helper function to check whether a row is blank before comparing it as an integer value.
     * Default value should be set to 0.
     * @param s
     * @param defaultValue
     * @return
     */
    private static int parseIntOrDefault(String s, int defaultValue){
        if (s == null || s.isBlank()) return defaultValue;
        return Integer.parseInt(s.trim());
    }

}
