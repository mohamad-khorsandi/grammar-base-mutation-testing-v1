package main;

import ast_tree.AstTreeGenerator;
import code_generation.RandomCodeGenerator;
import com.github.javaparser.Position;
import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import com.github.javaparser.utils.Pair;
import mutation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static main.MainUtils.*;


public class Main {
    public static RandomCodeGenerator randomCodeGenerator;
    public static Pair<Node, String> mutatedNode;
    public static HashMap<Node, String> NodeMapping = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String groundString = Files.readString(Path.of(Config.srcCodeFilePath));
        String resultCode = applyMutation(groundString).orElseThrow(() -> new RuntimeException("no place to mutate"));
        System.out.println("\n-------------------------resultCode:");
        System.out.println(resultCode);
    }

    public static Optional<String> applyMutation(String groundString) throws IOException {
        Node treeParent = new AstTreeGenerator(groundString).getTreeParent();

        if (treeParent.getParsed().equals(Node.Parsedness.UNPARSABLE))
            throw new RuntimeException("Input code can not be compiled!");

        Node funcParent = findFirstFunc(treeParent)
                .orElseThrow(() -> new RuntimeException("There is no method"));

        randomCodeGenerator = new RandomCodeGenerator(treeParent);

        ArrayList<Pair<Node, Mutable>> availMutableSegments = findAllMutable(funcParent);

        report(availMutableSegments);
        if (availMutableSegments.isEmpty())
            return Optional.empty();

        Pair<Node, Mutable> mutationTarget = getRandomElement(availMutableSegments);

        System.out.print("\n-------------------------mutation target : \n");
        MainUtils.printMutationSegment(mutationTarget);

        String mutatedSegment = mutationTarget.b.mutate(mutationTarget.a);

        System.out.print("\n-------------------------mutated segment : \n");
        System.out.println(mutatedSegment);

        return Optional.of(reproduceCode(groundString, mutationTarget.a.getRange().orElseThrow(), mutatedSegment));
    }

}
