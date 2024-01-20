import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MutationTestRunner {
    public static void main(String[] args) {
        String resourcesFolder = "src/test/java";

        List<Class<?>> testClasses = getTestClasses(resourcesFolder);

        runTests(testClasses);
    }

    private static List<Class<?>> getTestClasses(String folderPath) {
        List<Class<?>> testClasses = new ArrayList<>();

        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".java"));

        if (files != null) {
            for (File file : files) {
                String className = file.getName().replace(".java", "");
                try {
                    Class<?> clazz = Class.forName(className);
                    testClasses.add(clazz);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return testClasses;
    }

    private static void runTests(List<Class<?>> testClasses) {
        for (Class<?> testClass : testClasses) {
            System.out.println("Running tests for: " + testClass.getName());
            Result result = JUnitCore.runClasses(testClass);

            // Print the test results
            System.out.println("  - Ran " + result.getRunCount() + " tests");
            System.out.println("  - Passed: " + result.wasSuccessful());

            // Optionally, you can print more details about failures and errors
            for (Failure failure : result.getFailures()) {
                System.out.println("\nFailure: " + failure.toString());
            }
        }
    }
}
