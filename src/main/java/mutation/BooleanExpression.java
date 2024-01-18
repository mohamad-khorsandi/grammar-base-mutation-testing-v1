package mutation;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BinaryExpr;

import java.util.Set;

public class BooleanExpression implements Mutable {
    @Override
    public String mutate(Node node) {
        return null;
    }

    @Override
    public boolean isThisType(Node node) {
        if (node instanceof BinaryExpr) {
            String op = ((BinaryExpr) node).getOperator().asString();
            Set<String> intOp = Set.of("&&", "||", ">=", "==", "<=", "!=");
            return intOp.contains(op);
        }
        return false;
    }
}
