package mutation;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.NameExpr;

import java.util.concurrent.atomic.AtomicBoolean;

public class VariableExpression implements Mutable {
    @Override
    public String mutate(Node node) {
        return null;
    }

    @Override
    public boolean isThisType(Node node) {
        boolean p1 = node instanceof NameExpr;

        AtomicBoolean p2 = new AtomicBoolean(true);
        node.getParentNode().ifPresent((parent) ->
                p2.set(!(parent instanceof FieldAccessExpr)));

        return p1 && p2.get();
    }
}
