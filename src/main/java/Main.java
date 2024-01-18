import ast_tree.AstTreeGenerator;
import code_generation.RandomCodeGenerator;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BinaryExpr;
import mutation.Operator;


import java.io.FileNotFoundException;
import java.util.*;


public class Main {
    static Config config = new Config();
    static Random random = new Random();
    public static void main(String[] args) throws FileNotFoundException {
        Node treeParent = AstTreeGenerator.getTreeParent();

        // choose part

        // generate new code for chosen part

        // regenerate code from tree

        System.out.println();
    }
    static Node chooseTreeSegment(Node treeParent) {
        Node funcParent = BFSFirst(treeParent, "MethodDeclaration")
                .orElseThrow(() -> new RuntimeException("there is no method to test"));

        ArrayList<Node> availMutableSegments = BFSAll(funcParent, config.mutableSegments);

        int randomIndex = random.nextInt(availMutableSegments.size());
        return availMutableSegments.get(randomIndex);
    }

    static Optional<Node> BFSFirst(Node cur, String type) {
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(cur);

        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (cur.getClass().getSimpleName().equals(type))
                return Optional.of(cur);
            queue.addAll(cur.getChildNodes());
        }
        return Optional.empty();
    }

    static ArrayList<Node> BFSAll(Node cur, Set<String> types) {
        ArrayList<Node> result = new ArrayList<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(cur);

        while (!queue.isEmpty()) {
            cur = queue.poll();
            if (types.contains(cur.getClass().getSimpleName()))
                result.add(cur);
            queue.addAll(cur.getChildNodes());
        }
        return result;
    }
}

