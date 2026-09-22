package csv_classes;

import java.util.Locale;
import java.util.Objects;

public class Neighbourhood {
    private int neighbourhoodID;
    private String neighbourhoodName;
    private String ward;

    public Neighbourhood(int neighbourhoodID, String neighbourhoodName, String ward){
        this.neighbourhoodID = neighbourhoodID;
        this.neighbourhoodName = neighbourhoodName.toUpperCase();
        this.ward = ward;
    }

    public int getNeighbourhoodID(){
        return neighbourhoodID;
    }

    public String getNeighbourhoodName(){

        return neighbourhoodName;
    }

    public String getWard(){
        return ward;
    }

    @Override
    public String toString(){

        return neighbourhoodName + " (" + ward + ")";
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Neighbourhood other = (Neighbourhood) obj;
        return neighbourhoodID == other.neighbourhoodID
                && Objects.equals(neighbourhoodName, other.neighbourhoodName)
                && Objects.equals(ward, other.ward);
    }

    @Override
    public int hashCode(){
        return Objects.hash(neighbourhoodID, neighbourhoodName, ward);
    }
}
