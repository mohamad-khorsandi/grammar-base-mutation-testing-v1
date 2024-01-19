package code_generation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.stmt.BlockStmt;
import config.Config;


public class RandomCodeGenerator {
    private final GrammarParser grammarParser;
    ArrayList<Variable> variables = new ArrayList<>();

    public RandomCodeGenerator(Node treeParent) throws IOException {
        findVariablesRanges(treeParent);
        List<String> grammarRules = Files.readAllLines(Path.of(Config.grammarFilePath));
        this.grammarParser = new GrammarParser(grammarRules, "::=", variables);
//        generateRandomExpression(startTerminal, type, range);
    }

    private Range findVariableRange(Node node) {
        Node parent = node.getParentNode().orElse(null);
        while (!parent.getClass().equals(BlockStmt.class)) {
            parent = parent.getParentNode().orElse(null);
        }
        return parent.getRange().orElse(null);
    }

    private void findVariablesRanges(Node treeParent) {
        List<Node> children = treeParent.getChildNodes();
        for (Node child : children) {
            if (child.getClass() == VariableDeclarator.class) {
                String name = ((VariableDeclarator) child).getNameAsString();
                String type = ((VariableDeclarator) child).getTypeAsString();
                Range range = findVariableRange(child);
                variables.add(new Variable(range, type, name));
            }
            else {
                findVariablesRanges(child);
            }
        }
    }

    // Method to generate a random expression
    private void generateRandomExpression(String startTerminal, String type, Range range) {
        String[] result = generateExpression(startTerminal, type, range);
        System.out.println("Generated Expression: " + result[0].replaceAll("'","").replaceAll("\"", ""));
        System.out.println("Derivation Path: " + result[1]);
    }

    // Recursive method to generate an expression
    private String[] generateExpression(String nonTerminal, String type, Range range) {
        List<String> productions = grammarParser.getProductions(nonTerminal);
        if (productions == null || productions.isEmpty()) {
            return new String[]{nonTerminal, nonTerminal};
        } else {
            Random random = new Random();
            int selectedIndex = random.nextInt(productions.size());
            String selectedProduction = productions.get(selectedIndex);

            String[] symbols = selectedProduction.split("\\s+");
            StringBuilder result = new StringBuilder();
            StringBuilder derivationPath = new StringBuilder();

            boolean nameReplaced = false;

            for (String symbol : symbols) {
                if (!symbol.equals(grammarParser.getSeparator()) && !symbol.equals("|")) {
                    // Replace "<name>" if needed
                    if ("<name>".equals(symbol) && !nameReplaced && !grammarParser.getVariables().isEmpty()) {
                        ArrayList<Variable> validVariables = new ArrayList<Variable>();
                        for (Variable v: grammarParser.getVariables()) {
                            if (v.type.equals(type) && v.range.equals(range)) {
                                validVariables.add(v);
                            }
                        }
                        Random rnd = new Random();
                        symbol =  validVariables.get(rnd.nextInt(validVariables.size())).name;
                        nameReplaced = true;
                    }
                    // Recursive call to generateExpression
                    String[] subResult = generateExpression(symbol, type, range);
                    result.append(subResult[0]).append(" ");
                    derivationPath.append(subResult[1]).append(" ");
                }
            }

            // Append the derivation path
            derivationPath.append("[").append(nonTerminal).append(" ").append(grammarParser.getSeparator())
                    .append(" ").append(selectedProduction).append("]");

            return new String[]{result.toString().trim().replaceAll("\"", ""), derivationPath.toString().trim()};
        }
    }
}


class GrammarParser {
    private final List<String> grammarRules;
    private final String separator;
    private List<Variable> variables;

    // Constructor for GrammarParser
    public GrammarParser(List<String> grammarRules, String separator, List<Variable> variables) {
        this.grammarRules = grammarRules;
        this.separator = separator;
        this.variables = variables;
    }

    // Get productions for a non-terminal
    public List<String> getProductions(String nonTerminal) {
        for (String rule : grammarRules) {
            String[] parts = rule.split("\\s*" + separator + "\\s*");
            if (parts.length == 2 && parts[0].trim().equals(nonTerminal)) {
                return Arrays.asList(parts[1].split("\\s*\\|\\s*"));
            }
        }
        return null;
    }

    // Get the separator
    public String getSeparator() {
        return separator;
    }

    // Get the popped names list
    public List<Variable> getVariables() {
        return variables;
    }

    // Set the popped names list
    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }
}
