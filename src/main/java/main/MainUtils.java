package main;

import com.github.javaparser.ast.Node;
import com.github.javaparser.utils.Pair;
import mutation.Mutable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Random;

public class MainUtils {
    static Random random = new Random();

    static <T> T getRandomElement(ArrayList<T> list) {
        int randomIndex = random.nextInt(list.size());

        return list.get(randomIndex);
    }

    static Optional<Node> findFirstFunc(Node cur) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(cur);

        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur.getClass().getSimpleName().equals("MethodDeclaration"))
                return Optional.of(cur);
            queue.addAll(cur.getChildNodes());
        }
        return Optional.empty();
    }

    static ArrayList<Pair<Node, Mutable>> findAllMutable(Node cur) {
        ArrayList<Pair<Node, Mutable>> result = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(cur);

        while (!queue.isEmpty()) {
            cur = queue.poll();
            for (Mutable mutable : Config.mutableSegments) {
                if (mutable.isThisType(cur)) {
                    result.add(new Pair<>(cur, mutable));
                }
            }
            queue.addAll(cur.getChildNodes());
        }
        return result;
    }

    static void report(ArrayList<Pair<Node, Mutable>> availMutableSegments) {
        System.out.println("Possible Mutations:");
        for (var a : availMutableSegments) {
            printMutationSegment(a);
        }
    }
    static void printMutationSegment(Pair<Node, Mutable> a) {
        System.out.print(a.a.toString() + " (" + a.b.getClass().getSimpleName());
        System.out.println(", " + a.a.getRange().orElse(null) + ")");
    }
}
