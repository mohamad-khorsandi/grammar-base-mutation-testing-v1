package mutation;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.expr.BinaryExpr;
import main.Main;

import java.util.Optional;
import java.util.Set;

public class BooleanExpression extends Mutable {
    public BooleanExpression(String startTerminal, String forcedType) {
        super(startTerminal, forcedType);
    }

    @Override
    public String mutate(Node node) {
        double randomValue = Math.random();
        String st;
        String type;
        if (randomValue < 0.5){
            st = "<IntegerBaseBooleanExpression>";
            type = "int";
        }else{
            st = "<BooleanBaseBooleanExpression>";
            type = "boolean";
        }
        return Main.randomCodeGenerator.generate(st, type, node.getRange().orElseThrow());
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
