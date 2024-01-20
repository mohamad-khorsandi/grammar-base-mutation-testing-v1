package mutation;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import main.Main;

public class IntegerLiteral extends Mutable {
    public IntegerLiteral(String startTerminal, String forcedType) {
        super(startTerminal, forcedType);
    }

    @Override
    public boolean isThisType(Node node) {
        return node instanceof IntegerLiteralExpr;
    }
}
