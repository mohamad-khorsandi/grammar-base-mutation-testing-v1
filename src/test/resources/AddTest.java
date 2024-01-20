import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AddTest {

    Add adder = new Add();
    @Test
    public void testSumPositiveNumbers() {
        assertEquals(7, adder.sum(3, 4));
    }

    @Test
    public void testSumNegativeNumbers() {
        assertEquals(-5, adder.sum(-2, -3));
    }

    @Test
    public void testSumZeroAndPositiveNumber() {
        assertEquals(8, adder.sum(0, 8));
    }

    @Test
    public void testSumZeroAndNegativeNumber() {
        assertEquals(-6, adder.sum(0, -6));
    }
}
