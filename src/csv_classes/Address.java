package csv_classes;

public class Address {
    private Integer suiteNumber;
    private Integer houseNumber;
    private String streetName;

    public Address(int suiteNumber, int houseNumber, String streetName){
        this.suiteNumber = suiteNumber;
        this.houseNumber = houseNumber;
        this.streetName = streetName;
    }

    public int getSuiteNumber(){
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
        if (suiteNumber != null) result += suiteNumber + "-";
        if (houseNumber != null) result += houseNumber + " ";
        if (!streetName.isEmpty()) result += streetName;
        return result.trim();
    }
}
