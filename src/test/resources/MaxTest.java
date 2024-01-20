import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MaxTest {

    Max maxObject = new Max();
    @Test
    public void testMaxFirstGreaterThanSecond() {
        assertEquals(7, maxObject.max(7, 5));
    }

    @Test
    public void testMaxSecondGreaterThanFirst() {
        assertEquals(12, maxObject.max(8, 12));
    }

    @Test
    public void testMaxBothNumbersEqual() {
        assertEquals(5, maxObject.max(5, 5));
    }

    @Test
    public void testMaxNegativeNumbers() {
        assertEquals(-3, maxObject.max(-5, -3));
    }

    @Test
    public void testMaxOneNegativeOnePositive() {
        assertEquals(10, maxObject.max(10, -2));
    }
}
