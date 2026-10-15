package csv_classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistanceTest {

    private int testUnder50;
    private int testBetween50_100;
    private int testBetween100_150;
    private int testBetween150_200;
    private int testBetween200_300;
    private int testBetween300_500;
    private int testOver500;

    @BeforeEach
    void setUp() {
        testUnder50 = 25;
        testBetween50_100 = 75;
        testBetween100_150 = 125;
        testBetween150_200 = 175;
        testBetween200_300 = 250;
        testBetween300_500 = 400;
        testOver500 = 750;
    }

    @Test
    void fromDistance() {
        assertEquals(Distance.UNDER_50,Distance.fromDistance(testUnder50));
        assertNotEquals(Distance.UNDER_50, Distance.fromDistance(testBetween50_100));

        assertEquals(Distance.FROM_50_TO_100, Distance.fromDistance(testBetween50_100));
        assertNotEquals(Distance.FROM_50_TO_100, Distance.fromDistance(testUnder50));

        assertEquals(Distance.FROM_100_TO_150, Distance.fromDistance(testBetween100_150));
        assertNotEquals(Distance.FROM_100_TO_150, Distance.fromDistance(testUnder50));

        assertEquals(Distance.FROM_150_TO_200, Distance.fromDistance(testBetween150_200));
        assertNotEquals(Distance.FROM_150_TO_200, Distance.fromDistance(testUnder50));

        assertEquals(Distance.FROM_200_TO_300, Distance.fromDistance(testBetween200_300));
        assertNotEquals(Distance.FROM_200_TO_300, Distance.fromDistance(testUnder50));

        assertEquals(Distance.FROM_300_TO_500, Distance.fromDistance(testBetween300_500));
        assertNotEquals(Distance.FROM_300_TO_500, Distance.fromDistance(testUnder50));

        assertEquals(Distance.OVER_500, Distance.fromDistance(testOver500));
        assertNotEquals(Distance.OVER_500, Distance.fromDistance(testUnder50));
    }
}