package mutation;

import code_generation.Variable;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import main.Main;

import java.util.concurrent.atomic.AtomicBoolean;

public class VariableName extends Mutable {
    public VariableName(String startTerminal, String forcedType) {
        super(startTerminal, forcedType);
    }

    @Override
    public String mutate(Node node) {
        String varName = ((NameExpr) node).getName().toString();
        String varType = Main.randomCodeGenerator.variables.stream()
                .filter(v-> v.getName().equals(varName)).findFirst().orElseThrow().getType();
        return Main.randomCodeGenerator.generate(startTerminal, varType, node.getRange().orElseThrow());
    }

    @Override
    public boolean isThisType(Node node) {
        boolean p1 = node instanceof NameExpr;

        AtomicBoolean p2 = new AtomicBoolean(true);
        node.getParentNode().ifPresent((parent) ->
                p2.set(!(parent instanceof FieldAccessExpr)));

        AtomicBoolean p3 = new AtomicBoolean(true);
        node.getParentNode().ifPresent((parent) ->
                p3.set(!(parent instanceof MethodCallExpr)));

        return p1 && p2.get() && p3.get();
    }
}
