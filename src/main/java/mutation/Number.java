package mutation;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.DoubleLiteralExpr;

public class Number implements Mutable {

    @Override
    public String mutate(Node node) {
        String grammar = "grammar";
        return "";
    }

    @Override
    public boolean isThisType(Node node) {
        return node instanceof BooleanLiteralExpr || node instanceof DoubleLiteralExpr;
    }
}
