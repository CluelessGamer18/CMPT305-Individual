package csv_classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {
    private Location l1;
    private Location l1Copy;
    private Location l2;
    private Location edmonton1;
    private Location edmonton2;

    @BeforeEach
    void setUp() {
        l1 = new Location(1.00000000, -5.90000000);
        l2 = new Location(150.00000000, -40.00000000);
        l1Copy = new Location(1.00000000, -5.90000000);
        edmonton1 = new Location(53.45526, -113.42833); // Mill Woods TC
        edmonton2 = new Location(53.5212, -113.4572); // Bonnie Doon Mall
    }

    @Test
    void getLatitude() {
        assertEquals(1.00000000, l1.getLatitude());
        assertEquals(53.45526, edmonton1.getLatitude());
    }

    @Test
    void getLongitude() {
        assertEquals(-5.900000000, l1.getLongitude());
        assertEquals(-113.42833, edmonton1.getLongitude());
    }

    @Test
    void getDistance() {
        assertEquals(0, edmonton1.getDistance(edmonton1));
        assertEquals(7577, edmonton1.getDistance(edmonton2));

        assertEquals(edmonton1.getDistance(edmonton2), (edmonton2.getDistance(edmonton1)));
    }

    @Test
    void distanceToDowntown() {
        assertEquals(10659, edmonton1.distanceToDowntown());
    }

    @Test
    void testToString() {
        assertEquals("(1.00000000, -5.90000000)", l1.toString());
    }

    @Test
    void testEquals() {
        //reflexive
        assertEquals(l1, l1);

        //symmetric
        assertEquals(l1.equals(l1Copy), l1Copy.equals(l1));
        assertEquals(l1.equals(l2), l2.equals(l1));

        //transitive
        Location l1Copy2 = new Location(1.00000000, -5.90000000);
        if (l1.equals(l1Copy) && l1Copy.equals(l1Copy2)) {
            assertEquals(l1, l1Copy2);
        }
        if (!l2.equals(l1Copy) && l1Copy.equals(l1Copy2)) {
            assertNotEquals(l2, l1Copy2);
        }

        //false
        assertNotEquals(l1, null);  // x.equals(null) should return false.
        assertNotEquals(l1, "string");  // incorrect type

        // test all branches where false
        assertNotEquals(new Location(1.00000000, 2.11111111), new Location(210.00000000, 2.11111111));
        assertNotEquals(new Location(1.00000000, 2.11111111), new Location(1.0000000, 1.00000000));

    }

    @Test
    void testHashCode() {
        assertEquals(l1.hashCode(), l1Copy.hashCode());
    }
}