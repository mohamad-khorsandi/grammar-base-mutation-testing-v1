package mutation;

import code_generation.RandomCodeGenerator;
import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.DoubleLiteralExpr;
import main.Main;

import java.io.IOException;

public class DoubleLiteral extends Mutable {
    public DoubleLiteral(String startTerminal, String forcedType) {
        super(startTerminal, forcedType);
    }

    @Override
    public boolean isThisType(Node node) {
        return node instanceof DoubleLiteralExpr;
    }
}
