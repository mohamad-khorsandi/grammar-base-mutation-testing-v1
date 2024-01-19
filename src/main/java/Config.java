import mutation.*;

import java.util.Set;

public class Config {

    public Set<Mutable> mutableSegments = Set.of(
            new BooleanExpression(),
            new BooleanLiteral(),
            new DoubleLiteral(),
            new IntegerExpression(),
            new VariableExpression(),
            new IntegerLiteral()
    );
}
