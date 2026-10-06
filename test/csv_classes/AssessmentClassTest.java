package csv_classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssessmentClassTest {

    private AssessmentClass a1;
    private AssessmentClass a1Copy;
    private AssessmentClass a2;

    @BeforeEach
    void setUp() {
        a1 = new AssessmentClass("Residential", 100);
        a1Copy = new AssessmentClass("Residential", 100);
        a2 = new AssessmentClass("Commercial", 60);
    }

    @Test
    void getClassName() {
        assertEquals("Residential", a1.getClassName());
    }

    @Test
    void getPercentage() {
        assertEquals(100, a1.getPercentage());
    }

    @Test
    void testToString() {
        assertEquals("Residential 100%", a1.toString());
    }

    @Test
    void testEquals() {
        assertEquals(a1, a1);

        assertEquals(a1.equals(a1Copy), a1Copy.equals(a1));
        assertEquals(a1.equals(a2), a2.equals(a1));

        AssessmentClass a1Copy2 = new AssessmentClass("Residential", 100);
        if (a1.equals(a1Copy) && a1Copy.equals(a1Copy2)){
            assertEquals(a1, a1Copy2);
        }
        if (!a2.equals(a1Copy) && a1Copy.equals(a1Copy2)){
            assertNotEquals(a2, a1Copy2);
        }

        assertNotEquals(a1, null);
        assertNotEquals(a1, "String");

        assertNotEquals(new AssessmentClass("Residential", 100), new AssessmentClass("Commercial", 100));
        assertNotEquals(new AssessmentClass("Commercial", 100), new AssessmentClass("Commercial", 75));
    }

    @Test
    void testHashCode() {
        assertEquals(a1.hashCode(), a1Copy.hashCode());
    }
}