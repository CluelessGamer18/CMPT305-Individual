package csv_classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PropertyAssessmentTest {
    private PropertyAssessment p1;
    private PropertyAssessment p1Copy;
    private PropertyAssessment p2;
    private Address address1;
    private Address address2;
    private Neighbourhood neighbourhood1;
    private Neighbourhood neighbourhood2;
    private Location location1;
    private Location location2;
    private List<AssessmentClass> assessmentClasses1 = new ArrayList<>();
    private List<AssessmentClass> assessmentClasses2 = new ArrayList<>();

    @BeforeEach
    void setUp() {
        address1 = new Address("123E", 2000, "Street");
        address2 = new Address("450", 1200, "Avenue");
        neighbourhood1 = new Neighbourhood(100, "Downtown", "Ward");
        neighbourhood2 = new Neighbourhood(300, "South Side", "Draw");
        location1 = new Location(1.00000000, -4.00000000);
        location2 = new Location(9.00000000, 10.00000000);
        assessmentClasses1.add(new AssessmentClass("Residential", 100));
        assessmentClasses2.add(new AssessmentClass("Residential", 50));
        assessmentClasses2.add(new AssessmentClass("Commercial", 50));

        p1 = new PropertyAssessment(100, 10000, true, "TaxClass", address1, neighbourhood1, assessmentClasses1, location1);
        p1Copy = new PropertyAssessment(100, 10000, true, "TaxClass", address1, neighbourhood1, assessmentClasses1, location1);
        p2 = new PropertyAssessment(25, 20000, false, "ClassTax", address2, neighbourhood2, assessmentClasses2, location2);


    }

    @Test
    void getAccountNumber() {
        assertEquals(100, p1.getAccountNumber());
    }

    @Test
    void getAssessedValue() {
        assertEquals("$10,000", p1.getAssessedValue());
    }

    @Test
    void getAssessedValueRaw() {
        assertEquals(10000, p1.getAssessedValueRaw());
    }

    @Test
    void hasGarage() {
        assertTrue(p1.hasGarage());
    }

    @Test
    void getTaxClass() {
        assertEquals("TaxClass", p1.getTaxClass());
    }

    @Test
    void getAddress() {
        assertEquals(address1, p1.getAddress());
    }

    @Test
    void getNeighbourhood() {
        assertEquals(neighbourhood1, p1.getNeighbourhood());

    }

    @Test
    void getLocation() {
        assertEquals(location1, p1.getLocation());
    }

    @Test
    void getAssessmentClasses() {
        assertEquals(assessmentClasses1, p1.getAssessmentClasses());
    }

    @Test
    void testToString() {
        assertEquals("""
                Account Number: 100
                Assessed Value: $10,000
                Has Garage?: true
                Tax Class: TaxClass
                Address: 123E-2000 Street
                Neighbourhood: DOWNTOWN (ward)
                Assessment Classes: [Residential 100%]
                Location (lat/long): (1.00000000, -4.00000000)""", p1.toString());
    }

    @Test
    void testEquals() {

        assertEquals(p1, p1);
        assertEquals(p1.equals(p1Copy), p1Copy.equals(p1));
        assertEquals(p1.equals(p2), p2.equals(p1));

        PropertyAssessment p1Copy2 = new PropertyAssessment(100, 10000, true, "TaxClass", address1, neighbourhood1, assessmentClasses1, location1);
        if (p1.equals(p1Copy) && p1Copy.equals(p1Copy2)) {
            assertEquals(p1, p1Copy2);
        }
        if (!p2.equals(p1Copy) && p1Copy.equals(p1Copy2)) {
            assertNotEquals(p2, p1Copy2);
        }

        assertNotEquals(p1, null);
        assertNotEquals(p1, "String");

        assertNotEquals(new PropertyAssessment(100, 10000, true, "TaxClass", address1, neighbourhood1, assessmentClasses1, location1), new PropertyAssessment(400, 10000, true, "TaxClass", address1, neighbourhood1, assessmentClasses1, location1));
        assertNotEquals(new PropertyAssessment(100, 10000, true, "TaxClass", address1, neighbourhood1, assessmentClasses1, location1), new PropertyAssessment(100, 20000, true, "TaxClass", address1, neighbourhood1, assessmentClasses1, location1));
        assertNotEquals(new PropertyAssessment(100, 10000, true, "TaxClass", address1, neighbourhood1, assessmentClasses1, location1), new PropertyAssessment(100, 10000, false, "TaxClass", address1, neighbourhood1, assessmentClasses1, location1));
        assertNotEquals(new PropertyAssessment(100, 10000, true, "TaxClass", address1, neighbourhood1, assessmentClasses1, location1), new PropertyAssessment(100, 10000, true, "ClassTax", address1, neighbourhood1, assessmentClasses1, location1));
        assertNotEquals(new PropertyAssessment(100, 10000, true, "TaxClass", address1, neighbourhood1, assessmentClasses1, location1), new PropertyAssessment(100, 10000, true, "TaxClass", address2, neighbourhood1, assessmentClasses1, location1));
        assertNotEquals(new PropertyAssessment(100, 10000, true, "TaxClass", address1, neighbourhood1, assessmentClasses1, location1), new PropertyAssessment(100, 10000, true, "TaxClass", address1, neighbourhood2, assessmentClasses1, location1));
        assertNotEquals(new PropertyAssessment(100, 10000, true, "TaxClass", address1, neighbourhood1, assessmentClasses1, location1), new PropertyAssessment(100, 10000, true, "TaxClass", address1, neighbourhood1, assessmentClasses2, location1));
        assertNotEquals(new PropertyAssessment(100, 10000, true, "TaxClass", address1, neighbourhood1, assessmentClasses1, location1), new PropertyAssessment(100, 10000, true, "TaxClass", address1, neighbourhood1, assessmentClasses1, location2));

    }

    @Test
    void testHashCode() {
        assertEquals(p1.hashCode(), p1Copy.hashCode());
    }

    @Test
    void compareTo() {
        assertTrue(p1.compareTo(p2) < 0);
        assertTrue(p1.compareTo(p1Copy) == 0);
        assertTrue(p2.compareTo(p1) > 0);
    }
}