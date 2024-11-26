

import main.Main;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

public class MutationTestRunner {

    public static void main(String[] args) throws IOException {
        String projectRoot = "C:/Users/mohamad/IdeaProjects/mutation-testing-score-calculator/src/main/java/org/example/";
        for (var c : Code.values()) {
            Path path = Path.of(projectRoot + c.str);
            String code = Files.readString(path);
            String mutatedCode = nonEqMutate(code);
            Files.writeString(path, mutatedCode, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    public static String nonEqMutate(String groundString) throws IOException {
        String mutatedCode = null;
        do {
            mutatedCode = Main.applyMutation(groundString).orElseThrow();
        } while (mutatedCode.equals(groundString));
        return mutatedCode;
    }
}

enum Code {
    BINARY_SEARCH("BinarySearch"),
    BUBBLE_SORT("BubbleSort"),
    DIJKSTRA("Dijkstra"),
    BESSEL("Bessel"),
    GAMMA("Gamma"),

    KRUSKAL("Kruskal"),
    EDIT_DISTANCE("LevenshteinDistance"),
    MERGE_SORT("MergeSort");

    final String str;
    Code(String s) {
        str = s + ".java";
    }
}