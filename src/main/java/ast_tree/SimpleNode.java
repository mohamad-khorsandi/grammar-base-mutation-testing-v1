package ast_tree;

import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;

import java.util.ArrayList;

public class SimpleNode {
    public SimpleNode(Node node) {
        range = node.getRange().orElseThrow();
        this.type = node.getClass().getSimpleName();
        this.text = node.toString();
    }

    public SimpleNode() {
    }

    public Range range;
    public String text;
    public String type;
    public ArrayList<SimpleNode> children = new ArrayList<>();


    @Override
    public String toString() {
        return type;
    }
}