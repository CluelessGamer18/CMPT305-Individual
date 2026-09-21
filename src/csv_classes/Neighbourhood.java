package csv_classes;

public class Neighbourhood {
    private int neighbourhoodID;
    private String neighbourhoodName;
    private String ward;

    public Neighbourhood(int neighbourhoodID, String neighbourhoodName, String ward){
        this.neighbourhoodID = neighbourhoodID;
        this.neighbourhoodName = neighbourhoodName;
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
        return neighbourhoodName + ": " + neighbourhoodID + " Ward: " + ward;
    }
}
