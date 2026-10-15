package csv_classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProximityRecordsTest {

    private PropertyAssessment property;
    private ProximityRecords records;

    @BeforeEach
    void setUp() {
        String[] validRow = new String[]{
                "100", "12", "10001", "101 STREET", "1001", "NBH 1", "Ward 1", "100000", "Residential", "Y", "CLASS 1", "CLASS 2", "", "80", "20", "", "53.5078", "-113.51"
        };
        property = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        int nearestCommercialPropertyInMeters = 75;
        Distance group = Distance.fromDistance(nearestCommercialPropertyInMeters);

        records = new ProximityRecords(property, nearestCommercialPropertyInMeters, group);

    }

    @Test
    void getProperty() {
        assertSame(property, records.getProperty());
    }

    @Test
    void getGroup() {
        assertSame(Distance.FROM_50_TO_100, records.getGroup());
    }

    @Test
    void getNearestCommercialPropertyInMeters() {
        assertEquals(75, records.getNearestCommercialPropertyInMeters());
    }
}