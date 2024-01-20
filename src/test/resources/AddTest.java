import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AddTest {

    @Test
    public void testSumPositiveNumbers() {
        Add adder = new Add();
        assertEquals(7, adder.sum(3, 4));
    }

    @Test
    public void testSumNegativeNumbers() {
        Add adder = new Add();
        assertEquals(-5, adder.sum(-2, -3));
    }

    @Test
    public void testSumZeroAndPositiveNumber() {
        Add adder = new Add();
        assertEquals(8, adder.sum(0, 8));
    }

    @Test
    public void testSumZeroAndNegativeNumber() {
        Add adder = new Add();
        assertEquals(-6, adder.sum(0, -6));
    }
}
