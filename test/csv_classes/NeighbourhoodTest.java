package csv_classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NeighbourhoodTest {

    private Neighbourhood n1;
    private Neighbourhood n1Copy;
    private Neighbourhood n2;

    @BeforeEach
    void setUp() {
        n1 = new Neighbourhood(123, "Downtown", "Ward");
        n1Copy = new Neighbourhood(123, "Downtown", "Ward");
        n2 = new Neighbourhood(205, "South Side", "Draw");
    }

    @Test
    void getNeighbourhoodID() {
        assertEquals(123, n1.getNeighbourhoodID());
    }

    @Test
    void getNeighbourhoodName() {
        assertEquals("Downtown", n1.getNeighbourhoodName());
    }

    @Test
    void getWard() {
        assertEquals("Ward", n1.getWard());
    }

    @Test
    void testToString() {
        assertEquals("DOWNTOWN (ward)", n1.toString());
    }

    @Test
    void testEquals() {
        //reflexive
        assertEquals(n1, n1);

        //symmetric
        assertEquals(n1.equals(n1Copy), n1Copy.equals(n1));
        assertEquals(n1.equals(n2), n2.equals(n1));

        //transitive
        Neighbourhood n1Copy2 = new Neighbourhood(123, "Downtown", "Ward");
        if (n1.equals(n1Copy) && n1Copy.equals(n1Copy2)) {
            assertEquals(n1, n1Copy2);
        }
        if (!n2.equals(n1Copy) && n1Copy.equals(n2)) {
            assertNotEquals(n2, n1Copy2);
        }

        //false
        assertNotEquals(n1, null);  // x.equals(null) should return false.
        assertNotEquals(n2, "string");  // incorrect type

        // test all branches where false
        assertNotEquals(new Neighbourhood(123, "Downtown", "Ward"),
        new Neighbourhood(200, "Downtown", "Ward"));

        assertNotEquals(new Neighbourhood(123, "Downtown", "Ward"),
                new Neighbourhood(123, "South Side", "Ward"));

        assertNotEquals(new Neighbourhood(123, "Downtown", "Ward"),
                new Neighbourhood(123, "Downtown", "Draw"));


    }

    @Test
    void testHashCode() {
        assertEquals(n1.hashCode(), n1Copy.hashCode());
    }
}