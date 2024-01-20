package mutation;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.NameExpr;
import main.Main;

import java.util.concurrent.atomic.AtomicBoolean;

public class VariableName extends Mutable {
    public VariableName(String startTerminal, String forcedType) {
        super(startTerminal, forcedType);
    }

    @Override
    public String mutate(Node node) {

        return Main.randomCodeGenerator.generate(startTerminal, null, node.getRange().orElseThrow());
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
