import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ExampleCodeTest {

    @Test
    public void testPrimeFactorsOf12() {
        assertEquals("2 2 3", getPrimeFactors(12));
    }

    @Test
    public void testPrimeFactorsOf20() {
        assertEquals("2 2 5", getPrimeFactors(20));
    }

    @Test
    public void testPrimeFactorsOf99() {
        assertEquals("3 3 11", getPrimeFactors(99));
    }

    @Test
    public void testPrimeFactorsOf53() {
        assertEquals("53", getPrimeFactors(53));
    }

    @Test
    public void testPrimeFactorsOf1() {
        assertEquals("", getPrimeFactors(1));
    }

    private String getPrimeFactors(int number) {
        ExampleCode ec = new ExampleCode();

        // Redirect System.out to capture printed output
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        ec.findPrimeFactors(number);

        // Reset System.out to the original output stream
        System.setOut(System.out);

        return outContent.toString().trim();
    }
}
