public class ExampleCode {

    public static int performComplexOperations(int[] numbers) {
        int result = 0;

        for (int num : numbers) {
            result += num;

            if (num % 2 == 0) {
                result *= 2;
            } else {
                result -= 1;
            }

            if (num > 10) {
                result /= 2;
            }

            for (int i = 0; i < num; i++) {
                result += i;
            }

            if (num % 3 == 0) {
                if (num % 5 == 0) {
                    result *= 3;
                } else {
                    result /= 2;
                }
            }
        }

        if (result > 100) {
            result = customOperation(result);
        }

        return result;
    }

    private static int customOperation(int value) {
        return value * 2;
    }
}
