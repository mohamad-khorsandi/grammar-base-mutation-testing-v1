package code_generation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class RandomCodeGenerator {
    private final GrammarParser grammarParser;
    ArrayList<Variable> variables = new ArrayList<>();

    public RandomCodeGenerator(String grammarFilePath, String startTerminal, List<String> poppedNames) throws IOException {
        //todo
        List<String> grammarRules = Files.readAllLines(Path.of(grammarFilePath));
        this.grammarParser = new GrammarParser(grammarRules, "::=", poppedNames);
        generateRandomExpression(startTerminal);
    }



//    public static String generate(String nonTerminalName, Range range) {
//        Rule r = Grammar.getRule(nonTerminalName);
//        System.out.println(r.toString());
//        return "code";
//    }

    // Method to generate a random expression
    private void generateRandomExpression(String startTerminal) {
        String[] result = generateExpression(startTerminal);
        System.out.println("Generated Expression: " + result[0].replaceAll("'","").replaceAll("\"", ""));
        System.out.println("Derivation Path: " + result[1]);
    }

    // Recursive method to generate an expression
    private String[] generateExpression(String nonTerminal) {
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
                    if ("<name>".equals(symbol) && !nameReplaced && !grammarParser.getPoppedNames().isEmpty()) {
                        symbol = grammarParser.getPoppedNames().remove(0);
                        nameReplaced = true;
                    }
                    // Recursive call to generateExpression
                    String[] subResult = generateExpression(symbol);
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
    private List<String> poppedNames;

    // Constructor for GrammarParser
    public GrammarParser(List<String> grammarRules, String separator, List<String> poppedNames) {
        this.grammarRules = grammarRules;
        this.separator = separator;
        this.poppedNames = poppedNames;
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
    public List<String> getPoppedNames() {
        return poppedNames;
    }

    // Set the popped names list
    public void setPoppedNames(List<String> poppedNames) {
        this.poppedNames = poppedNames;
    }
}
