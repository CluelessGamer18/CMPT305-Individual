package csv_classes;

import java.io.IOException;
import java.util.*;

public class CommercialProxy {
    // Initialize min and max values for properties to get rid of extreme outliers
    private static final int MIN_RESIDENTIAL_VALUE = 50000;
    private static final int MAX_RESIDENTIAL_VALUE = 3000000;

    // A home is near a commercial property when it is within this many meters.
    private static final int NEAR_THRESHOLD_METERS = 150;

    // Minimum number of properties a neighbourhood needs for comparison
    private static final int MINIMUM_GROUP_SIZE = 30;

    // Grid cell size in degrees, used for both latitude and longitude
    private static final double CELL_SIZE_DEGREES = 0.016;

    // Multiplier used to merge row and column together
    // This value must be larger than the biggest column so different cells never share the same key
    private static final long CELL_KEY_MULTIPLIER = 100000L;

    // Returned when there are no commercial properties nearby
    private static final int NO_COMMERCIAL_NEARBY = Integer.MAX_VALUE;

    private final PropertyAssessments assessments;
    private  PropertyAssessments residential;
    private  PropertyAssessments commercial;
    private PropertyAssessments totalResidential;
    private  int removedBelowMinValue;
    private int removedAboveMaxValue;

    private Map<Long, List<Location>> commercialIndex;

    private List<ProximityRecords> records;

    private double nearFarDistance;
    private int groupsCompared;

    public CommercialProxy(String filename) throws IOException {
        assessments = PropertyAssessments.fromCsv(filename);
        cleanData();
        buildCommercialIndex();
        calculateProximity();
        compareNearAndFarProperties();
    }

    private void cleanData() {
        totalResidential = assessments.filterByTaxClass("Residential");

        PropertyAssessments residentialBeforeFilters = totalResidential.filterByValueRange(MIN_RESIDENTIAL_VALUE, Integer.MAX_VALUE);
        removedBelowMinValue = totalResidential.size() - residentialBeforeFilters.size();
        residential = residentialBeforeFilters.filterByValueRange(MIN_RESIDENTIAL_VALUE, MAX_RESIDENTIAL_VALUE);
        removedAboveMaxValue = residentialBeforeFilters.size() - residential.size();

        commercial = assessments.filterByTaxClass("Non Residential");
    }

    private void buildCommercialIndex() {
        commercialIndex = new HashMap<>();

        for (PropertyAssessment pa: commercial.getAssessments()) {
            Location location = pa.getLocation();
            long key = cellKey(cellRow(location), cellCol(location));

            commercialIndex.computeIfAbsent(key, _ -> new ArrayList<>()).add(location);
        }
    }

    // Which latitude band a location belongs to
    private int cellRow(Location location) {
        return (int) Math.floor(location.getLatitude() / CELL_SIZE_DEGREES);
    }

    // Which longitude band a location belongs to
    private int cellCol(Location location) {
        return (int) Math.floor(location.getLongitude() / CELL_SIZE_DEGREES);
    }

    // Combine row and column into one long to use as my map key
    private long cellKey(int row, int col) {
        return row * CELL_KEY_MULTIPLIER * col;
    }

    private int nearestCommercialDistance(Location house) {
        int houseRow = cellRow(house);
        int houseCol = cellCol(house);
        int nearest = NO_COMMERCIAL_NEARBY; // Initially

        // Search the house's grid cell and those around it for commercial properties
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int colOffset = -1; colOffset <= 1; colOffset++) {
                long key = cellKey(houseRow + rowOffset, houseCol + colOffset);
                List<Location> locations = commercialIndex.get(key);

                if (locations == null) {
                    continue;
                }

                for (Location commercialLocation : locations) {
                    int distanceTo = house.getDistance(commercialLocation);
                    if (distanceTo < nearest) {
                        nearest = distanceTo;
                    }
                }
            }
        }

        return nearest;
    }

    private void calculateProximity() {
        records = new ArrayList<>();

        for (PropertyAssessment property : residential.getAssessments()) {
            Location location = property.getLocation();
            int distance = nearestCommercialDistance(location);

            records.add(new ProximityRecords(property, distance, Distance.fromDistance(distance)));
        }
    }

    /**
     * Compare near and far homes inside groups of comparable houses (same neighbourhood and type: house or condo)
     */
    private void compareNearAndFarProperties() {
        Map<String, List<ProximityRecords>> groups = new HashMap<>();

        for (ProximityRecords record: records) {
            PropertyAssessment house = record.getProperty();
            String type = house.getAddress().getSuiteNumber().isBlank() ? "house" : "condo";
            String key = house.getNeighbourhood().getNeighbourhoodName() + " | " + type;
            groups.computeIfAbsent(key, _ -> new ArrayList<>()).add(record);
        }

        List<Double> differences = new ArrayList<>();
        for (List<ProximityRecords> group : groups.values()) {
            PropertyAssessments near = new PropertyAssessments();
            PropertyAssessments far = new PropertyAssessments();

            for (ProximityRecords record: group) {
                if (record.getNearestCommercialPropertyInMeters() <= NEAR_THRESHOLD_METERS) {
                    near.addAssessment(record.getProperty());
                } else {
                    far.addAssessment(record.getProperty());
                }
            }

            // Neighbourhood is not large enough / would skew results somewhat
            if (near.size() < MINIMUM_GROUP_SIZE || far.size() < MINIMUM_GROUP_SIZE) {
                continue;
            }

            double farMedian = far.median();
            if (farMedian == 0) {
                continue;
            }

            differences.add((near.median() - farMedian) * 100 / farMedian);
        }

        groupsCompared = differences.size();
        nearFarDistance = median(differences);
    }

    /**
     * Returns a median of a sorted List<Double>
     * @return median value of sorted List<Double>
     */
    private double median(List<Double> values) {
        if (values.isEmpty()) {
            return 0;
        }
        List<Double> sorted = new ArrayList<>(values);
        Collections.sort(sorted);
        int mid = sorted.size() / 2;
        if (sorted.size() % 2 != 0) {
            return sorted.get(mid);
        }
        return (sorted.get(mid - 1) + sorted.get(mid)) / 2.0;
    }

    public String getDatasetSummary() {
        return String.format("Number of Records:                        %,d%n", assessments.size())
                + String.format("Residential Properties Before Filter:     %,d%n", totalResidential.size())
                + String.format("  Removed Under $%, d:                    %,d%n", MIN_RESIDENTIAL_VALUE, removedBelowMinValue)
                + String.format("  Removed Above $%, d:                    %,d%n", MAX_RESIDENTIAL_VALUE, removedAboveMaxValue)
                + String.format("  Residential Properties Used:            %,d%n", residential.size())
                + String.format("Commercial Properties:                    %,d%n", commercial.size());
    }

    public double getPercentWithin(int meters) {
        int count = 0;
        for (ProximityRecords record: records) {
            if (record.getNearestCommercialPropertyInMeters() <= meters) {
                count++;
            }
        }
        return count * 100.0 / records.size();
    }

    /**
     * Houses grouped by their distance band
     * @return Map of distances of property assessments
     */
    public Map<Distance, PropertyAssessments> getHomeByDistanceBand() {
        Map<Distance, PropertyAssessments> groups = new EnumMap<>(Distance.class);
        for (Distance band: Distance.values()) {
            groups.put(band, new PropertyAssessments());
        }
        for (ProximityRecords record: records) {
            groups.get(record.getGroup()).addAssessment(record.getProperty());
        }
        return groups;
    }

    public double getNearFarDistance() {
        return nearFarDistance;
    }

    public int getGroupsCompared() {
        return groupsCompared;
    }

    public int getNearThresholdMeters() {
        return NEAR_THRESHOLD_METERS;
    }
}
