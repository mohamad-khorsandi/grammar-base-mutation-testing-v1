package mutation;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.NameExpr;

public class VarName implements Mutable {
    @Override
    public String mutate(Node node) {
        return null;
    }

    @Override
    public boolean isThisType(Node node) {
        return node instanceof NameExpr;
    }
}
