package mutation;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BinaryExpr;
import main.Main;

import java.util.Set;

public class BooleanExpression extends Mutable {
    public BooleanExpression(String startTerminal, String forcedType) {
        super(startTerminal, forcedType);
    }

    @Override
    public boolean isThisType(Node node) {
        if (node instanceof BinaryExpr) {
            String op = ((BinaryExpr) node).getOperator().asString();
            Set<String> intOp = Set.of("&&", "||", ">=", "==", "<=", "!=", ">", "<");
            return intOp.contains(op);
        }
        return false;
    }
}
