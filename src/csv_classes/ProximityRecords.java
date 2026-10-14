package csv_classes;

public class ProximityRecords {
    private final PropertyAssessment property;
    private final int nearestCommercialPropertyInMeters;
    private final Distance group;

    public ProximityRecords(PropertyAssessment property, int nearestCommercialPropertyInMeters, Distance group) {
        this.property = property;
        this.nearestCommercialPropertyInMeters = nearestCommercialPropertyInMeters;
        this.group = group;
    }

    public PropertyAssessment getProperty() {
        return property;
    }

    public Distance getGroup() {
        return group;
    }

    public int getNearestCommercialPropertyInMeters() {
        return nearestCommercialPropertyInMeters;
    }

}
