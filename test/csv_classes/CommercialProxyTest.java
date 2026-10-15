package csv_classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CommercialProxyTest {

    private CommercialProxy proxy;
    private Location house;
    private int expectedThreshold;

    @BeforeEach
    void setUp() throws IOException {
        proxy = new CommercialProxy("Property_Assessment_Data_2026_Test - Copy.csv");

        String[] validRow = new String[]{
                "100", "12", "10001", "101 STREET", "1001", "NBH 1", "Ward 1", "100000", "Residential", "Y", "CLASS 1", "CLASS 2", "", "80", "20", "", "53.5078", "-113.51"
        };

        house = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0).getLocation();
        expectedThreshold = 150;

    }

    @Test
    void nearestCommercialDistance() {
        // Set exact location of a commercial property
        assertEquals(0, proxy.nearestCommercialDistance(new Location(53.63951338889562, -113.4861465)));

        // Check that there is a commercial property nearby
        // Numbers are generated using my haversine formula in Location
        assertEquals(492, proxy.nearestCommercialDistance(new Location(53.616319727904084, -113.4718609)));

        // Check that there is no commercial property nearby
        // Nearest commercial property is far away
        assertEquals(Integer.MAX_VALUE, proxy.nearestCommercialDistance(new Location(53.436014211845055, -113.6633712)));
    }

    @Test
    void getDatasetSummary() {
        int MIN_RESIDENTIAL_VALUE = 50000;
        int MAX_RESIDENTIAL_VALUE = 3000000;
        PropertyAssessments assessments = proxy.getAssessments();
        PropertyAssessments totalResidential = proxy.getTotalResidential();
        int removedBelowMinValue = proxy.getRemovedBelowMinValue();
        int removedAboveMaxValue = proxy.getRemovedAboveMaxValue();
        PropertyAssessments residential = proxy.getResidential();
        PropertyAssessments commercial = proxy.getCommercial();
        assertEquals(String.format("Number of Records:                        %,d%n", 41)
                + String.format("Residential Properties Before Filter:     %,d%n", 22)
                + String.format("  Removed Under $%, d:                    %,d%n", 50000, 1)
                + String.format("  Removed Above $%, d:                    %,d%n", 3000000, 1)
                + String.format("  Residential Properties Used:            %,d%n", 20)
                + String.format("Commercial Properties:                    %,d%n", 12),
                String.format("Number of Records:                        %,d%n", assessments.size())
                + String.format("Residential Properties Before Filter:     %,d%n", totalResidential.size())
                + String.format("  Removed Under $%, d:                    %,d%n", MIN_RESIDENTIAL_VALUE, removedBelowMinValue)
                + String.format("  Removed Above $%, d:                    %,d%n", MAX_RESIDENTIAL_VALUE, removedAboveMaxValue)
                + String.format("  Residential Properties Used:            %,d%n", residential.size())
                + String.format("Commercial Properties:                    %,d%n", commercial.size()));
    }

    @Test
    void getPercentWithin() {

    }

    @Test
    void getHomeByDistanceBand() {
    }

    @Test
    void getNearFarDistance() {
    }

    @Test
    void getGroupsCompared() {
    }

    @Test
    void getNearThresholdMeters() {
        assertEquals(expectedThreshold, proxy.getNearThresholdMeters());
    }

    @Test
    void getAssessments() {
        PropertyAssessments assessments = proxy.getAssessments();
        assertEquals(41, assessments.size());
        
    }

    @Test
    void getTotalResidential() {
        PropertyAssessments totalResidential = proxy.getTotalResidential();
        assertEquals(22, totalResidential.size());

        for (PropertyAssessment pa: totalResidential.getAssessments()) {
            assertEquals("Residential", pa.getTaxClass());
        }

    }

    @Test
    void getRemovedBelowMinValue() {
        int getRemovedBelowMin = proxy.getRemovedBelowMinValue();
        assertEquals(1, getRemovedBelowMin);

    }

    @Test
    void getRemovedAboveMaxValue(){
        int getRemovedAboveMax = proxy.getRemovedAboveMaxValue();
        assertEquals(1, getRemovedAboveMax);

    }

    @Test
    void getResidential() {
        PropertyAssessments residential = proxy.getResidential();
        assertEquals(20, residential.size());

        for (PropertyAssessment pa: residential.getAssessments()) {
            assertEquals("Residential", pa.getTaxClass());
        }

    }

    @Test
    void getCommercial() {
        PropertyAssessments commercial = proxy.getCommercial();
        assertEquals(12, commercial.size());

        for (PropertyAssessment pa: commercial.getAssessments()) {
            assertEquals("Non Residential", pa.getTaxClass());
        }
    }

}