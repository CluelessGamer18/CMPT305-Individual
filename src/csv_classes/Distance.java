package csv_classes;

public enum Distance {
    // Distances in meters
    UNDER_50,
    FROM_50_TO_100,
    FROM_100_TO_150,
    FROM_150_TO_200,
    FROM_200_TO_300,
    FROM_300_TO_500,
    OVER_500;

    public static Distance fromDistance(int meters) {
        if (meters < 50) {
            return UNDER_50;
        } else if (meters < 100) {
            return FROM_50_TO_100;
        } else if (meters < 150) {
            return FROM_100_TO_150;
        } else if (meters < 200) {
            return FROM_150_TO_200;
        } else if (meters < 300) {
            return FROM_200_TO_300;
        } else if (meters < 500) {
            return FROM_300_TO_500;
        } else {
            return OVER_500;
        }
    }
}
