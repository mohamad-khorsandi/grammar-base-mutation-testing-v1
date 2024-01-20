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

    public Range getRange() {
        return range;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

}
