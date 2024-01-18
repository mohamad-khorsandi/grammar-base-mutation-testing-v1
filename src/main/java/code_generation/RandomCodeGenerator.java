package code_generation;

import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import grammer_parser.Grammar;
import grammer_parser.Rule;

import java.util.ArrayList;


public class RandomCodeGenerator {
    public RandomCodeGenerator(Node parent) {
        //todo
    }

    ArrayList<Variable> variables = new ArrayList<>();

    public static String generate(String nonTerminalName, Range range, String varName) {
        Rule r = Grammar.getRule(nonTerminalName);
        System.out.println(r.toString());
        return "code";
    }
}
