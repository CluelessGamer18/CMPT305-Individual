package csv_classes;

import java.util.Objects;

public class Location {
    private Double latitude;
    private Double longitude;

    public Location(Double latitude, Double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude(){
        return latitude;
    }

    public Double getLongitude(){
        return longitude;
    }

    @Override
    public String toString(){
        return "(" + latitude + ", " + longitude + ")";
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Location other = (Location) obj;
        return Double.compare(latitude, other.latitude) == 0
                && Double.compare(longitude, other.longitude) == 0;
    }

    @Override
    public int hashCode(){
        return Objects.hash(latitude, longitude);
    }
}
