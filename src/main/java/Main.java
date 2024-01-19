import ast_tree.AstTreeGenerator;
import com.github.javaparser.ast.Node;
import com.github.javaparser.utils.Pair;
import mutation.*;


import java.io.FileNotFoundException;
import java.util.*;


public class Main {
    static Config config = new Config();
    static Random random = new Random();
    public static void main(String[] args) throws FileNotFoundException {
        Node treeParent = AstTreeGenerator.getTreeParent();

        if (treeParent.getParsed().equals(Node.Parsedness.UNPARSABLE))
            throw new RuntimeException("input code can not be compiled");

        Node funcParent = findFirstFunc(treeParent)
                .orElseThrow(() -> new RuntimeException("there is no method"));

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
            for (Mutable mutable : config.mutableSegments) {
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
