package csv_classes;

import java.util.List;

public class PropertyAssessment {
    private int accountNumber;
    private int assessedValue;
    private boolean garage;
    private String taxClass;
    private Address address;
    private Neighbourhood neighbourhood;
    private Location location;
    private List<AssessmentClass> assessmentClasses;

    public PropertyAssessment(int accountNumber, int assessedValue, boolean garage, String taxClass, Address address, Neighbourhood neighbourhood, List<AssessmentClass> assessmentClasses, Location location){
        this.accountNumber = accountNumber;
        this.assessedValue = assessedValue;
        this.garage = garage;
        this.taxClass = taxClass;
        this.address = address;
        this.neighbourhood = neighbourhood;
        this.assessmentClasses = assessmentClasses;
        this.location = location;
    }

    public int getAccountNumber(){
        return accountNumber;
    }

    public int getAssessedValue(){
        return assessedValue;
    }

    public boolean hasGarage(){
        return garage;
    }

    public String getTaxClass(){
        return taxClass;
    }

    public String getAddress(){
        return address.toString();
    }

    public String getneighbourhood(){
        return neighbourhood.toString();
    }

    public String getLocation(){
        return location.toString();
    }

    public List<AssessmentClass> getAssessmentClasses(){
        return assessmentClasses;
    }

}
