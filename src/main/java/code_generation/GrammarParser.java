package code_generation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class GrammarParser {
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
    public Optional<List<String>> getProductions(String nonTerminal) {
        for (String rule : grammarRules) {
            String[] parts = rule.split("\\s*" + separator + "\\s*");
            if (parts.length == 2 && parts[0].trim().equals(nonTerminal)) {
                return Optional.of(Arrays.asList(parts[1].split("\\s*\\|\\s*")));
            }
        }
        return Optional.empty();
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
