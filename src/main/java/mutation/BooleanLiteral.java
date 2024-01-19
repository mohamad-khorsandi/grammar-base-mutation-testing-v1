package mutation;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;

public class BooleanLiteral implements Mutable{
    @Override
    public String mutate(Node node) {
        return null;
    }

    @Override
    public boolean isThisType(Node node) {
        return node instanceof BooleanLiteralExpr;
    }
}
