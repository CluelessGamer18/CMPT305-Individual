package csv_classes;

import java.util.Objects;

public class Address {
    private String suiteNumber;
    private int houseNumber = 0;
    private String streetName;

    public Address(String suiteNumber, int houseNumber, String streetName){
        this.suiteNumber = suiteNumber;
        this.houseNumber = houseNumber;
        this.streetName = streetName;
    }

    public String getSuiteNumber(){
        return suiteNumber;
    }

    public int gethouseNumber(){
        return houseNumber;
    }

    public String getStreetName(){
        return streetName;
    }

    @Override
    public String toString(){
        String result  = "";
        if (!suiteNumber.isEmpty()) result += suiteNumber + "-";
        if (houseNumber != 0) result += houseNumber + " ";
        if (!streetName.isEmpty()) result += streetName;
        return result.trim();
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Address other = (Address) obj;
        return Objects.equals(suiteNumber, other.suiteNumber)
                && houseNumber == other.houseNumber
                && Objects.equals(streetName, other.streetName);
    }

    @Override
    public int hashCode(){
        return Objects.hash(suiteNumber, houseNumber, streetName);
    }
}
