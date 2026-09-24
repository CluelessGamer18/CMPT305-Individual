package csv_classes;

import java.util.List;
import java.util.Objects;

public class PropertyAssessment implements Comparable<PropertyAssessment>{
    private final int accountNumber;
    private final int assessedValue;
    private final boolean garage;
    private final String taxClass;
    private final Address address;
    private final Neighbourhood neighbourhood;
    private final Location location;
    private final List<AssessmentClass> assessmentClasses;

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

    public String getAssessedValue(){
        return "$" + String.format("%,d", assessedValue);
    }

    public int getAssessedValueRaw(){
        return assessedValue;
    }

    public boolean hasGarage(){
        return garage;
    }

    public String getTaxClass(){
        return taxClass;
    }

    public Address getAddress(){
        return address;
    }

    public Neighbourhood getNeighbourhood(){
        return neighbourhood;
    }

    public Location getLocation(){
        return location;
    }

    public List<AssessmentClass> getAssessmentClasses(){
        return assessmentClasses;
    }

    @Override
    public String toString(){
        return "Account Number: " + getAccountNumber() + "\nAssessed Value: " + getAssessedValue() + "\nHas Garage?: " + hasGarage()
                + "\nTax Class: " + getTaxClass() + "\nAddress: " + getAddress().toString() + "\nNeighbourhood: " + getNeighbourhood().toString()
                + "\nAssessment Classes: " + getAssessmentClasses() + "\nLocation (lat/long): " + getLocation().toString();
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

    @Override
    public int compareTo(PropertyAssessment other){
        return Integer.compare(this.assessedValue, other.assessedValue);
    }

}
