package mutation;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import main.Main;

public class BooleanLiteral extends Mutable {
    public BooleanLiteral(String startTerminal, String forcedType) {
        super(startTerminal, forcedType);
    }

    @Override
    public boolean isThisType(Node node) {
        return node instanceof BooleanLiteralExpr;
    }
}
