package csv_classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    private Address a1;
    private Address a1Copy;
    private Address a2;

    @BeforeEach
    void setUp() {
        a1 = new Address("123F", 123, "Street");
        a1Copy = new Address("123F", 123, "Street");
        a2 = new Address("2104", 1303, "Avenue");
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
    }

    @Test
    void testEquals() {
    }

    @Test
    void testHashCode() {
        assertEquals(a1.hashCode(), a1Copy.hashCode());
    }
}