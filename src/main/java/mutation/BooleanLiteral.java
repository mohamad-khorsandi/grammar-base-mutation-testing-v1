package mutation;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import main.Main;

public class BooleanLiteral implements Mutable{
    @Override
    public String mutate(Node node) {
        Main.randomCodeGenerator.generate("startTerminal", "boolean", node.getRange().get());
        return null;
    }

    @Override
    public boolean isThisType(Node node) {
        return node instanceof BooleanLiteralExpr;
    }
}
