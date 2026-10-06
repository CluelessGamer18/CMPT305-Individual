package csv_classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    private Address a1;
    private Address a1Copy;
    private Address a2;
    private Address aNoSuite;
    private Address aNoNumber;
    private Address aNoStreet;

    @BeforeEach
    void setUp() {
        a1 = new Address("123F", 123, "Street");
        a1Copy = new Address("123F", 123, "Street");
        a2 = new Address("2104", 1303, "Avenue");
        aNoSuite = new Address("", 123, "Street");
        aNoNumber = new Address("123F",0 , "Street");
        aNoStreet = new Address("123F", 123, "");

    }

    @Test
    void getSuiteNumber() {
        assertEquals("123F", a1.getSuiteNumber());
    }

    @Test
    void gethouseNumber() {
        assertEquals(123, a1.gethouseNumber());
    }

    @Test
    void getStreetName() {
        assertEquals("Street", a1.getStreetName());
    }

    @Test
    void testToString() {
        assertEquals("123F-123 Street", a1.toString());
        assertEquals("123 Street", aNoSuite.toString());
        assertEquals("123F-Street", aNoNumber.toString());
        assertEquals("123F-123", aNoStreet.toString());
    }

    @Test
    void testEquals() {
        assertEquals(a1, a1);

        assertEquals(a1.equals(a1Copy), a1Copy.equals(a1));
        assertEquals(a1.equals(a2), a2.equals(a1));

        Address a1Copy2 = new Address("123F", 123, "Street");
        if (a1.equals(a1Copy) && a1Copy.equals(a1Copy2)) {
            assertEquals(a1, a1Copy2);
        }
        if (!a2.equals(a1Copy) && a1Copy.equals(a1Copy2)) {
            assertNotEquals(a2, a1Copy2);
        }

        assertNotEquals(a1, null);
        assertNotEquals(a1, "String");

        assertNotEquals(new Address("123E", 2504, "Street"), new Address("199", 2504, "Street"));
        assertNotEquals(new Address("123E", 2504, "Street"), new Address("123E", 1000, "Street"));
        assertNotEquals(new Address("123E", 2504, "Street"), new Address("123E", 2504, "Avenue"));

    }

    @Test
    void testHashCode() {
        assertEquals(a1.hashCode(), a1Copy.hashCode());
    }
}