import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MaxTest {

    @Test
    public void testMaxFirstGreaterThanSecond() {
        Max maxObject = new Max();
        assertEquals(7, maxObject.max(7, 5));
    }

    @Test
    public void testMaxSecondGreaterThanFirst() {
        Max maxObject = new Max();
        assertEquals(12, maxObject.max(8, 12));
    }

    @Test
    public void testMaxBothNumbersEqual() {
        Max maxObject = new Max();
        assertEquals(5, maxObject.max(5, 5));
    }

    @Test
    public void testMaxNegativeNumbers() {
        Max maxObject = new Max();
        assertEquals(-3, maxObject.max(-5, -3));
    }

    @Test
    public void testMaxOneNegativeOnePositive() {
        Max maxObject = new Max();
        assertEquals(10, maxObject.max(10, -2));
    }
}
