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

        Node funcParent = findFirstFunc(treeParent)
                .orElseThrow(() -> new RuntimeException("there is no method"));

        ArrayList<Pair<Node, Mutable>> availMutableSegments = findAllMutable(funcParent);
        System.out.println("here are available mutations");
        for (var a : availMutableSegments) {
            System.out.println(a.a + " (" + a.b.getClass().getSimpleName() + ")");
        }
        int randomIndex = random.nextInt(availMutableSegments.size());

        Pair<Node, Mutable> mutationTarget = availMutableSegments.get(randomIndex);

        mutationTarget.b.mutate(mutationTarget.a);

        System.out.println();
        // regenerate code from tree
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

}
