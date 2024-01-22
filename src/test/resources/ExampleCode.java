import java.util.Scanner;

public class ExampleCode {
    int n;
    public void findPrimeFactors() {
        // Print 2 as a factor if n is even
        while (n % 2 == 0) {
            System.out.print("2 ");
            n /= 2;
        }

        // Check odd factors from 3 onwards
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            while (n % i == 0) {
                System.out.print(i + " ");
                n /= i;
            }
        }

        // If n becomes a prime greater than 2
        if (n > 2) {
            System.out.print(n);
        }
    }
}
