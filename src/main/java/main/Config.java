package main;

import mutation.*;
import java.util.Set;

public class Config {
    static public String grammarFilePath = "src/main/resources/grammar";
    static public String srcCodeFilePath = "src/main/resources/code.java";

    static public Set<Mutable> mutableSegments = Set.of(
            new BooleanExpression("<BooleanExpression>", "boolean"),
            new BooleanLiteral("<BooleanLiteral>", null),
            new DoubleLiteral("<DoubleLiteral>", null),
            new IntegerExpression("<IntegerExpression>", "int"),
            new VariableName("<VariableName>", null),
            new IntegerLiteral("<IntegerLiteral>", null)
    );
}
