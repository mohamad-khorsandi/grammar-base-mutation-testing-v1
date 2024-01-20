package ast_tree;
import code_generation.Variable;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import main.Config;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class AstTreeGenerator {
    private Node treeParent = null;
    private SimpleNode simpleTreeParent = null;
    final private String groundString;

    public AstTreeGenerator(String groundString) {
        this.groundString = groundString;
    }

    private void generateAst() throws FileNotFoundException {
        JavaParser javaParser = new JavaParser();
        ParseResult<CompilationUnit> cu = javaParser.parse(this.groundString);

        treeParent = cu.getResult().orElseThrow();

        simpleTreeParent = copyTree(treeParent);
    }

    private SimpleNode copyTree(Node node) {
        SimpleNode myTreeNode = new SimpleNode(node);
        for(Node n : node.getChildNodes()) {
            myTreeNode.children.add(copyTree(n));
        }

        return myTreeNode;
    }

    public Node getTreeParent() {
        if (treeParent == null) {
            try {
                generateAst();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return treeParent;
    }
}

