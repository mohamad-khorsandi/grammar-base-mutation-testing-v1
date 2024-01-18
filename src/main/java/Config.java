import mutation.BooleanExpression;
import mutation.IntegerExpression;
import mutation.Mutable;
import mutation.VarName;

import java.util.Set;

public class Config {

    public Set<Mutable> mutableSegments = Set.of(
            new BooleanExpression(),
            new IntegerExpression(),
            new VarName(),
            new BooleanExpression()
    );
}
