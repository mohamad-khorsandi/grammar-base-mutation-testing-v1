package mutation;

import com.github.javaparser.ast.Node;
import main.Main;

abstract public class Mutable {
    String startTerminal;
    String forcedType;

    public Mutable(String startTerminal, String forcedType) {
        this.startTerminal = startTerminal;
        this.forcedType = forcedType;
    }

    public String mutate(Node node) {
        return Main.randomCodeGenerator.generate(startTerminal, null, node.getRange().orElseThrow());
    }

    public abstract boolean isThisType(Node node);
}
