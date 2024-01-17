package grammer_parser;
import java.util.*;
import static grammer_parser.Type.findType;

public class Grammar {
    private Grammar() {

    }

    static ArrayList<Rule> rules = null;

    public static Rule getRule(String name) {
        if (rules == null)
            parseFile("src/main/resources/grammar");
        return rules.stream().filter(rule -> rule.first.equals(name)).findFirst().orElseThrow(() ->
                new RuntimeException("there is no such a non-terminal"));
    }

    public static void parseFile(String path) throws RuntimeException {
        rules = new ArrayList<>();
        String file = Utils.readFile(path, false);
        String pattern = "^<.+>::=";
        Rule curRule = null;

        for (String token : List.of(file.split("\\s+"))) {
            if (token.matches(pattern)) {
                curRule = new Rule(token.substring(0, token.length()-3));
                rules.add(curRule);

            } else if (curRule == null) {
                throw new RuntimeException();
            } else {
                Type st = findType(token).orElseThrow(() -> new RuntimeException(token + " is invalid in grammar"));
                curRule.addItem(token, st);
            }
        }
    }
}
