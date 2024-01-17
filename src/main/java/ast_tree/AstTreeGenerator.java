package ast_tree;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class AstTreeGenerator {
    private static Node treeParent = null;
    private static SimpleNode simpleTreeParent = null;

    private static void generateAst() throws FileNotFoundException {
        String path = "src/main/resources/codedotjava";

        JavaParser javaParser = new JavaParser();
        ParseResult<CompilationUnit> cu = javaParser.parse(new FileInputStream(path), StandardCharsets.UTF_8);

        treeParent = cu.getResult().orElseThrow();

        simpleTreeParent = copyTree(treeParent);
    }

    private static SimpleNode copyTree(Node node) {
        SimpleNode myTreeNode = new SimpleNode(node);
        for(Node n : node.getChildNodes()) {
            myTreeNode.children.add(copyTree(n));
        }

        return myTreeNode;
    }

    public Node getTreeParent() throws FileNotFoundException {
        if (treeParent == null)
            generateAst();
        return treeParent;
    }

    public SimpleNode getSimpleTreeParent() throws FileNotFoundException {
        if (simpleTreeParent == null)
            generateAst();
        return simpleTreeParent;
    }
}
