import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MutationTestRunner {
    public static void main(String[] args) {
        int totalMutations = 0;
        int killedMutations = 0;
        String resourcesFolder = "src/test/resources";

        List<Class<?>> testClasses = getTestClasses(resourcesFolder);
        
        runTests(testClasses);

        double mutationScore = (double) killedMutations / totalMutations;
        System.out.println("\nMutation Score: " + (mutationScore * 100) + "%");
    }

    private static List<Class<?>> getTestClasses(String folderPath) {
        List<Class<?>> testClasses = new ArrayList<>();

        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith("Test.java"));

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

            System.out.println("  - Ran " + result.getRunCount() + " tests");
            System.out.println("  - Passed: " + result.wasSuccessful());

            for (Failure failure : result.getFailures()) {
                System.out.println("\nFailure: " + failure.toString());
            }
        }
    }
}
