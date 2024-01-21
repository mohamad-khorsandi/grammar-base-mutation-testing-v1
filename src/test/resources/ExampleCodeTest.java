import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExampleCodeTest {

    @Test
    public void testPerformComplexOperationsWithEmptyArray() {
        int[] numbers = {};
        int result = ExampleCode.performComplexOperations(numbers);

        assertEquals(0, result);
    }

    @Test
    public void testPerformComplexOperationsWithNegativeNumbers() {
        int[] numbers = {-3, 8, -15, 5, -12};
        int result = ExampleCode.performComplexOperations(numbers);

        assertEquals(74, result);
    }

    @Test
    public void testPerformComplexOperationsWithLargeNumbers() {
        int[] numbers = {100, 200, 300, 400, 500};
        int result = ExampleCode.performComplexOperations(numbers);

        assertEquals(832700, result);
    }
}