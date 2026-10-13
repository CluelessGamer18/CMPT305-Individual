package csv_classes;

import java.util.Objects;

public class Location {
    private final Double latitude;
    private final Double longitude;
    // Have a control value for downtown values, catches outliers
    // Using approximate coordinates for Churchill Square as "City Center"
    private static final Location DOWNTOWN = new Location(53.54389, -113.48972);

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

    /*
     * Haversine formula for converting latitude and longitude values to meters
     * Resources for formula:
     * https://en.wikipedia.org/wiki/Haversine_formula
     * https://stackoverflow.com/questions/27928/calculate-distance-between-two-latitude-longitude-points-haversine-formula
     */
    public int getDistance(Location other) {
        double latDistance = Math.toRadians(other.latitude - latitude);
        double longDistance = Math.toRadians(other.longitude - longitude);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(other.latitude)) * Math.cos(Math.toRadians(latitude))
                * Math.sin(longDistance / 2) * Math.sin(longDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Radius of Earth in meters
        double earthRadius = 6371000.0;
        return (int) (Math.round(earthRadius * c));
    }

    public int distanceToDowntown(){
        return getDistance(DOWNTOWN);
    }

    @Override
    public String toString(){
        return "(" + String.format("%.8f", latitude) + ", " + String.format("%.8f", longitude) + ")";
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
