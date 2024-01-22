import main.Main;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class MutationTestRunner {
    private static int killedMutations = 0;
    private static int totalMutations = 0;
    static String originalCode;

    private static final int NUM_ITERATIONS = 20;
    private static final String ORIGINAL_CODE_FILE_PATH = "src/test/resources/ExampleCode.java";
    private static final String MUTATED_CODE_FOLDER = "src/test/resources/";

    public static void main(String[] args) throws IOException {
        Set<String> uniqueMutations = new HashSet<>();
        originalCode = getCodeFromFile();

        for (int i = 1; i <= NUM_ITERATIONS; i++) {
            System.out.println("Running Iteration " + i);


            Optional<String> mutatedCodeOptional = Main.applyMutation(originalCode);

            if (mutatedCodeOptional.isPresent()) {
                String mutatedCode = mutatedCodeOptional.get();

                if (uniqueMutations.add(mutatedCode)) {
                    saveMutatedCode(mutatedCode);

                    System.out.println("Running tests for: " + ExampleCodeTest.class.getName());
                    Result result = JUnitCore.runClasses(ExampleCodeTest.class);
                    System.out.println("  - Ran " + result.getRunCount() + " tests");
                    System.out.println("  - Passed: " + result.wasSuccessful());

                    if (!result.wasSuccessful()) {
                        killedMutations++;
                    }
                    totalMutations++;
                } else {
                    System.out.println("Skipped - Mutation is repetitive");
                }
            } else {
                System.out.println("Skipped - Mutation returned Optional.empty()");
                saveMutatedCode(originalCode);
            }

        }
        saveMutatedCode(originalCode);

        double mutationScore = (double) killedMutations / totalMutations;
        System.out.println("\nFinal Mutation Score: " + (mutationScore * 100) + "%");
    }

    private static String getCodeFromFile() {
        try {
            return new String(Files.readAllBytes(Paths.get(MutationTestRunner.ORIGINAL_CODE_FILE_PATH)));
        } catch (IOException e) {
            throw new RuntimeException("Error reading original code file", e);
        }
    }

    private static void saveMutatedCode(String mutatedCode) {
        String mutatedFilePath = MUTATED_CODE_FOLDER + "ExampleCode.java";
        try {
            Files.write(Paths.get(mutatedFilePath), mutatedCode.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error Saving Mutated Code", e);
        }
    }
}
