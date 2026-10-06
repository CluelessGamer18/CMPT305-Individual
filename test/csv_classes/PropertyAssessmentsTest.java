package csv_classes;

import static org.junit.jupiter.api.Assertions.*;

class PropertyAssessmentsTest {
    private PropertyAssessment assessment1;
    private PropertyAssessment assessment2;
    private PropertyAssessments test;
    private String[] validRow;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        validRow = new String[]{
                "100", "12", "10001", "101 STREET", "1001", "NBH 1", "Ward 1", "100000", "Residential", "Y", "CLASS 1", "", "", "100", "", "","53.5078", "-113.51"
        };
        test = new PropertyAssessments();

    }

    @org.junit.jupiter.api.Test
    void buildAssessments() {
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        assertEquals(100, assessment1.getAccountNumber());
        assertEquals("12", assessment1.getAddress().getSuiteNumber());
        assertEquals(10001, assessment1.getAddress().gethouseNumber());
        assertEquals("101 STREET", assessment1.getAddress().getStreetName());
        assertEquals(1001, assessment1.getNeighbourhood().getNeighbourhoodID());
        assertEquals("NBH 1", assessment1.getNeighbourhood().getNeighbourhoodName());
        assertEquals("Ward 1", assessment1.getNeighbourhood().getWard());
        assertEquals(100000, assessment1.getAssessedValueRaw());
        assertEquals("Residential", assessment1.getTaxClass());
        assertTrue(assessment1.hasGarage());
        AssessmentClass ac = assessment1.getAssessmentClasses().getFirst();
        assertEquals("CLASS 1", ac.getClassName());
        assertEquals(100, ac.getPercentage());
        assertEquals(53.5078, assessment1.getLocation().getLatitude());
        assertEquals(-113.51, assessment1.getLocation().getLongitude());

        //Where suite is empty
        validRow[1] = "";
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        assertEquals("", assessment1.getAddress().getSuiteNumber());

        //Where house number is empty
        validRow[2] = "";
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        assertEquals(0, assessment1.getAddress().gethouseNumber());

        //Where street name is empty
        validRow[3] = "";
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        assertEquals("", assessment1.getAddress().getStreetName());

        //Where neighbourhood ID is empty
        validRow[4] = "";
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        assertEquals(0, assessment1.getNeighbourhood().getNeighbourhoodID());

        //Where garage is false
        validRow[9] = "N";
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        assertFalse(assessment1.hasGarage());
    }

    @org.junit.jupiter.api.Test
    void addAssessment() {
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        test.addAssessment(assessment1);
        assessment2 = test.get(0);

        assertEquals(assessment2, assessment1);
    }

    @org.junit.jupiter.api.Test
    void getAssessments() {
    }

    @org.junit.jupiter.api.Test
    void filterById() {
    }

    @org.junit.jupiter.api.Test
    void filterByNeighbourhood() {
    }

    @org.junit.jupiter.api.Test
    void filterByAssessmentClass() {
    }

    @org.junit.jupiter.api.Test
    void contains() {
    }

    @org.junit.jupiter.api.Test
    void sum() {
    }

    @org.junit.jupiter.api.Test
    void min() {
    }

    @org.junit.jupiter.api.Test
    void size() {
    }

    @org.junit.jupiter.api.Test
    void max() {
    }

    @org.junit.jupiter.api.Test
    void range() {
    }

    @org.junit.jupiter.api.Test
    void mean() {
    }

    @org.junit.jupiter.api.Test
    void median() {
    }
}