package ast_tree;

import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;

import java.util.ArrayList;

public class SimpleNode {
    public SimpleNode(Node node) {
        Range range = node.getRange().orElseThrow();
        this.startLine = range.begin.line;
        this.startColumn = range.begin.column;
        this.endLine = range.end.line;
        this.endColumn = range.end.column;
        this.type = node.getClass().getSimpleName();
        this.text = node.toString();
    }

    public SimpleNode() {
    }

    public int startLine;
    public int endLine;
    public int startColumn;
    public int endColumn;
    public String text;
    public String type;
    public ArrayList<SimpleNode> children = new ArrayList<>();


    @Override
    public String toString() {
        return type;
    }
}