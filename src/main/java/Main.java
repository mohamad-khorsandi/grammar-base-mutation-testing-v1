import ast_tree.AstTreeGenerator;
import code_generation.RandomCodeGenerator;
import com.github.javaparser.ast.Node;
import com.github.javaparser.utils.Pair;
import config.Config;
import mutation.*;


import java.io.IOException;
import java.util.*;


public class Main {
    static Random random = new Random();
    static RandomCodeGenerator randomCodeGenerator;

    public static void main(String[] args) throws IOException {
        Node treeParent = AstTreeGenerator.getTreeParent();

        if (treeParent.getParsed().equals(Node.Parsedness.UNPARSABLE))
            throw new RuntimeException("input code can not be compiled");

        Node funcParent = findFirstFunc(treeParent)
                .orElseThrow(() -> new RuntimeException("there is no method"));

        randomCodeGenerator = new RandomCodeGenerator(funcParent);

        ArrayList<Pair<Node, Mutable>> availMutableSegments = findAllMutable(funcParent);

        report(availMutableSegments);

        Pair<Node, Mutable> mutationTarget = getRandomElement(availMutableSegments);

        mutationTarget.b.mutate(mutationTarget.a);

        // regenerate code from tree
    }

    public static <T> T getRandomElement(ArrayList<T> list) {
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
        System.out.println("possible mutations:");
        for (var a : availMutableSegments) {
            System.out.println(a.a + " (" + a.b.getClass().getSimpleName() + ")");
        }
    }
}
