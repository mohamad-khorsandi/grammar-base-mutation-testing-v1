package code_generation;

import com.github.javaparser.Range;

public class Variable {
    Range range;
    String type;
    String name;

    public Variable(Range range, String type, String name) {
        this.range = range;
        this.type = type;
        this.name = name;
    }
}
