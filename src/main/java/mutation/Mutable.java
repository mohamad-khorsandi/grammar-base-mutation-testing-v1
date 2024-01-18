package mutation;

import com.github.javaparser.ast.Node;

public interface Mutable {
    String mutate(Node node);

    boolean isThisType(Node node);
}
