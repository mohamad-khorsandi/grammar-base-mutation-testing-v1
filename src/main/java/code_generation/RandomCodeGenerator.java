package code_generation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import com.github.javaparser.Range;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.stmt.BlockStmt;
import main.Config;


public class RandomCodeGenerator {
    private final GrammarParser grammarParser;
    public ArrayList<Variable> variables = new ArrayList<>();
    private final Random random = new Random();

    public RandomCodeGenerator(Node treeParent) throws IOException {
        findVariablesRanges(treeParent);
        List<String> grammarRules = Files.readAllLines(Path.of(Config.grammarFilePath));
        this.grammarParser = new GrammarParser(grammarRules, "::=", variables);
    }

    private Range findVariableRange(Node node) {
        Node parent = node.getParentNode().orElseThrow();
        while (!(parent instanceof BlockStmt || parent instanceof ClassOrInterfaceDeclaration)) { //todo
            parent = parent.getParentNode().orElseThrow();
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
    public String generate(String startTerminal, String type, Range range) {
        ArrayList<Variable> validVariables = getValidVariables(type, range);
        String result = generateMultiRule(startTerminal, validVariables);
        return result.replaceAll("'","").replaceAll("\"", "");
    }

    // Recursive method to generate an expression
    private String generateMultiRule(String nonTerminal, ArrayList<Variable> validVariables) {
        List<String> productions = grammarParser.getProductions(nonTerminal).orElseThrow();
        if (validVariables.isEmpty())
            productions = productions.stream().filter(s -> !s.contains("<name>"))
                    .collect(Collectors.toList());

        if (productions.isEmpty())
            throw new RuntimeException("no valid generation rule found for " +
                    "(this may mean that there was no valid variable to replace <name> and no alternative grammar):" + nonTerminal);

        int selectedIndex = random.nextInt(productions.size());

        String selectedProduction = productions.get(selectedIndex);

        StringBuilder result = generateSingleRule(selectedProduction, validVariables);

        return result.toString().trim().replaceAll("\"", "");
    }

    private StringBuilder generateSingleRule(String selectedProduction, ArrayList<Variable> validVariables) {
        String[] symbols = selectedProduction.split("\\s+");
        StringBuilder result = new StringBuilder();

        boolean nameReplaced = false;
        for (String symbol : symbols) {
            if (!symbol.equals(grammarParser.getSeparator()) && !symbol.equals("|")) {
                // Replace "<name>" if needed
                if ("<name>".equals(symbol) && !nameReplaced && !grammarParser.getVariables().isEmpty()) {
                    symbol = validVariables.get(random.nextInt(validVariables.size())).name;
                    nameReplaced = true;
                }
                // Recursive call to generateExpression
                String subResult;
                if (symbol.startsWith("<")) {
                    subResult = generateMultiRule(symbol, validVariables);
                } else {
                    subResult = symbol;
                }
                result.append(subResult).append(" ");
            }
        }
        return result;
    }

    ArrayList<Variable> getValidVariables(String type, Range range) {
        ArrayList<Variable> validVariables = new ArrayList<>();
        for (Variable v: grammarParser.getVariables()) {
            if (v.type.equals(type) && v.range.contains(range)) {
                validVariables.add(v);
            }
        }
        return validVariables;
    }

}


