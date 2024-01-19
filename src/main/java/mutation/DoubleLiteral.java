package mutation;

import code_generation.RandomCodeGenerator;
import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.DoubleLiteralExpr;

import java.io.IOException;

public class DoubleLiteral implements Mutable {

    @Override
    public String mutate(Node node) {
        String grammar = "grammar";
        return "";
    }

    @Override
    public boolean isThisType(Node node) {
        return node instanceof DoubleLiteralExpr;
    }
}
