package csv_classes;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PropertyAssessmentsTest {
    private PropertyAssessment assessment1;
    private PropertyAssessment assessment2;
    private PropertyAssessment assessment3;
    private PropertyAssessments test;
    private PropertyAssessments returnValue;
    private String[] validRow;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        validRow = new String[]{
                "100", "12", "10001", "101 STREET", "1001", "NBH 1", "Ward 1", "100000", "Residential", "Y", "CLASS 1", "CLASS 2", "", "80", "20", "","53.5078", "-113.51"
        };
        test = new PropertyAssessments();
        returnValue = new PropertyAssessments();


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
        assertEquals(80, ac.getPercentage());
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
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        test.addAssessment(assessment1);
        List<PropertyAssessment> listValue = test.getAssessments();
        assertEquals(1, listValue.size());
        assertEquals(assessment1, listValue.getFirst());

    }

    @org.junit.jupiter.api.Test
    void filterById() {
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        test.addAssessment(assessment1);
        //test empty
        assertNull(test.filterById(20));
        assertEquals(assessment1, test.filterById(100));

        assertNotEquals(assessment2, test.filterById(100));

    }

    @org.junit.jupiter.api.Test
    void filterByNeighbourhood() {
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        test.addAssessment(assessment1);
        //test empty
        assertEquals(0, test.filterByNeighbourhood("Non - Existent").size());
        returnValue = test.filterByNeighbourhood("NBH 1");
        assertEquals(assessment1, returnValue.get(0));

        assertNotEquals(assessment2, returnValue.get(0));
    }

    @org.junit.jupiter.api.Test
    void filterByAssessmentClass() {
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        test.addAssessment(assessment1);
        //test empty
        assertEquals(0, test.filterByAssessmentClass("Non - Existent").size());
        returnValue = test.filterByAssessmentClass("class 1");
        assertEquals(assessment1, returnValue.get(0));

        assertNotEquals(assessment2, returnValue.get(0));

    }

    @org.junit.jupiter.api.Test
    void contains() {
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        test.addAssessment(assessment1);

        assertTrue(test.contains(assessment1));
    }

    @org.junit.jupiter.api.Test
    void sum() {
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        assessment2 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        //test empty
        assertEquals(0, test.sum());

        test.addAssessment(assessment1);
        test.addAssessment(assessment2);

        assertEquals(200000, test.sum());
    }

    @org.junit.jupiter.api.Test
    void min() {
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        validRow[7] = "200000";
        assessment2 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        validRow[7] = "50";
        assessment3 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);

        //test empty
        assertEquals(0, test.min());

        test.addAssessment(assessment1);
        test.addAssessment(assessment2);
        test.addAssessment(assessment3);

        assertEquals(50, test.min());
    }

    @org.junit.jupiter.api.Test
    void size() {
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        //test empty
        assertEquals(0, test.size());
        test.addAssessment(assessment1);

        assertEquals(1, test.size());
    }

    @org.junit.jupiter.api.Test
    void max() {
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        validRow[7] = "200000";
        assessment2 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        //test empty
        assertEquals(0, test.max());

        test.addAssessment(assessment1);
        test.addAssessment(assessment2);

        assertEquals(200000, test.max());

    }

    @org.junit.jupiter.api.Test
    void range() {
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        validRow[7] = "200000";
        assessment2 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        //test empty
        assertEquals(0, test.range());

        test.addAssessment(assessment1);
        test.addAssessment(assessment2);

        assertEquals(100000, test.range());
    }

    @org.junit.jupiter.api.Test
    void mean() {
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        validRow[7] = "200000";
        assessment2 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        //test empty
        assertEquals(0, test.mean());

        test.addAssessment(assessment1);
        test.addAssessment(assessment2);

        assertEquals(150000, test.mean());
    }

    @org.junit.jupiter.api.Test
    void median() {
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        validRow[7] = "200000";
        assessment2 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        validRow[7] = "300000";
        assessment3 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        //test empty
        assertEquals(0, test.median());

        //Test even
        test.addAssessment(assessment1);
        test.addAssessment(assessment2);
        assertEquals(150000, test.median());

        //Test odd
        test.addAssessment(assessment3);
        assertEquals(200000, test.median());
    }

    @org.junit.jupiter.api.Test
    void parseIntOrDefault(){
        validRow[2] = null;
        assessment1 = PropertyAssessments.buildAssessments(new String[][]{validRow}).get(0);
        assertEquals(0, assessment1.getAddress().gethouseNumber());

    }

    @org.junit.jupiter.api.Test
    void fromCSV() throws java.io.IOException {
        PropertyAssessments result = PropertyAssessments.fromCsv("Property_Assessment_Data_2026.csv");
        assertEquals(439634, result.size());
        assertEquals(4058129, result.get(0).getAccountNumber());
        assertEquals(4058137, result.get(1).getAccountNumber());

        assertThrows(java.io.IOException.class, () -> PropertyAssessments.fromCsv("Does_Not_Exist"));
    }
}