package csv_classes;

import java.util.List;
import java.util.Objects;

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

    public String getNeighbourhood(){
        return neighbourhood.toString();
    }

    public String getLocation(){
        return location.toString();
    }

    public List<AssessmentClass> getAssessmentClasses(){
        return assessmentClasses;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        PropertyAssessment other = (PropertyAssessment) obj;
        return accountNumber == other.accountNumber
                && assessedValue == other.assessedValue
                && garage == other.garage
                && Objects.equals(taxClass, other.taxClass)
                && Objects.equals(address, other.address)
                && Objects.equals(neighbourhood, other.neighbourhood)
                && Objects.equals(assessmentClasses, other.assessmentClasses)
                && Objects.equals(location, other.location);
    }

    @Override
    public int hashCode(){
        return Objects.hash(accountNumber, assessedValue, garage, taxClass, address, neighbourhood, assessmentClasses, location);
    }

}
