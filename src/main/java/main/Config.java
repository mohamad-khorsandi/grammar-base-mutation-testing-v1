package main;

import mutation.*;
import java.util.Set;

public class Config {
    static public String grammarFilePath = "src/main/resources/grammar";
    static public String srcCodeFilePath = "src/main/resources/code.java";

    static public Set<Mutable> mutableSegments = Set.of(
            new BooleanExpression(),
            new BooleanLiteral(),
            new DoubleLiteral(),
            new IntegerExpression(),
            new VariableExpression(),
            new IntegerLiteral()
    );
}
