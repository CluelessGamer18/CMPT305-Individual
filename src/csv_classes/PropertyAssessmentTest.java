///

package csv_classes;

import java.util.ArrayList;
import java.util.List;
public class PropertyAssessmentTest {

    public static void main(String[] args) {
        testConstructorAndGetters();
        testEqualsAndHashCode();
        testCompareTo();
        testToString();

        System.out.println("All tests completed.");
    }

    private static void testConstructorAndGetters() {
        Address address = new Address(203, 14348, "115 AVENUE NW");
        Neighbourhood neighbourhood = new Neighbourhood(1090, "CANORA", "WIHKWENTOWIN");
        Location location = new Location(53.5591, -113.5321);
        List<AssessmentClass> classes = new ArrayList<>();
        classes.add(new AssessmentClass("RESIDENTIAL", 45));
        classes.add(new AssessmentClass("Commercial", 55));

        PropertyAssessment p = new PropertyAssessment(
                10001234, 450000, true, "Residential",
                address, neighbourhood, classes, location);

        assertEquals(10001234, p.getAccountNumber(), "accountNumber");
        assertEquals("$450,000", p.getAssessedValue(), "assessedValue");
        assertEquals(true, p.hasGarage(), "garage");
        assertEquals("Residential", p.getTaxClass(), "taxClass");
        assertEquals(address.toString(), p.getAddress(), "address (string)");
        assertEquals(neighbourhood.toString(), p.getNeighbourhood(), "neighbourhood (string)");
        assertEquals(location.toString(), p.getLocation(), "location (string)");
        assertEquals(classes, p.getAssessmentClasses(), "assessmentClasses");
    }

    private static void testEqualsAndHashCode() {
        Address address = new Address(203, 14348, "115 AVENUE NW");
        Neighbourhood neighbourhood = new Neighbourhood(1090, "CANORA", "WIHKWENTOWIN");
        Location location = new Location(53.5591, -113.5321);

        List<AssessmentClass> classes1 = new ArrayList<>();
        classes1.add(new AssessmentClass("RESIDENTIAL", 100));

        List<AssessmentClass> classes2 = new ArrayList<>();
        classes2.add(new AssessmentClass("RESIDENTIAL", 100));

        PropertyAssessment p1 = new PropertyAssessment(
                10001234, 450000, true, "Residential",
                address, neighbourhood, classes1, location);
        PropertyAssessment p2 = new PropertyAssessment(
                10001234, 450000, true, "Residential",
                address, neighbourhood, classes2, location);
        PropertyAssessment p3 = new PropertyAssessment(
                99999999, 100000, false, "Commercial",
                address, neighbourhood, classes1, location);

        assertEquals(true, p1.equals(p2), "p1 equals p2 (same values, separate list instances)");
        assertEquals(false, p1.equals(p3), "p1 equals p3 (different values)");
        assertEquals(true, p1.hashCode() == p2.hashCode(), "hashCode matches for equal objects");
    }

    private static void testCompareTo() {
        Address address = new Address(0, 100, "MAIN ST");
        Neighbourhood neighbourhood = new Neighbourhood(1, "TEST", "TEST WARD");
        Location location = new Location(0.0, 0.0);
        List<AssessmentClass> classes = new ArrayList<>();
        classes.add(new AssessmentClass("RESIDENTIAL", 100));

        PropertyAssessment low = new PropertyAssessment(1, 100000, false, "Residential",
                address, neighbourhood, classes, location);
        PropertyAssessment high = new PropertyAssessment(2, 500000, false, "Residential",
                address, neighbourhood, classes, location);

        assertEquals(true, low.compareTo(high) < 0, "low compares less than high (by assessedValue)");
        assertEquals(true, high.compareTo(low) > 0, "high compares greater than low");
        assertEquals(true, low.compareTo(low) == 0, "low compares equal to itself");
    }

    private static void testToString() {
        Address address = new Address(203, 14348, "115 AVENUE NW");
        Neighbourhood neighbourhood = new Neighbourhood(1090, "CANORA", "WIHKWENTOWIN");
        Location location = new Location(53.559155555, -113.5321000001);
        List<AssessmentClass> classes = new ArrayList<>();
        classes.add(new AssessmentClass("RESIDENTIAL", 45));
        classes.add(new AssessmentClass("COMMERCIAL", 55));

        PropertyAssessment p = new PropertyAssessment(10001234, 450000, true, "Residential",
                address, neighbourhood, classes, location);

        System.out.println("toString output:\n" + p.toString());
        assertEquals(true, p.toString() != null && !p.toString().isEmpty(), "toString is not empty");
        assertEquals(true, p.toString().contains("10001234"), "toString contains account number");
    }

    // Small helper so we don't need JUnit
    private static void assertEquals(Object expected, Object actual, String label) {
        if (expected == null ? actual == null : expected.equals(actual)) {
            System.out.println("PASS: " + label);
        } else {
            System.out.println("FAIL: " + label + " (expected " + expected + ", got " + actual + ")");
        }
    }
}