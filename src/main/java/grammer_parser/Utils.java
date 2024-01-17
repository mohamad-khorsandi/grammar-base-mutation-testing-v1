package grammer_parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Utils {

    static public String readFile(String fileName, boolean print) {
        String inputString = null;
        try {
            inputString = Files.readString(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] lines = inputString.split("\n");
        StringBuilder result = new StringBuilder();
        for (String line : lines) {
            String trimmedLine = line.replaceAll("\\s+$", "");
            if (!trimmedLine.isEmpty()) {
                result.append(trimmedLine).append("\n");
            }
        }

        String resultString = result.toString().stripTrailing();
        resultString = resultString.replaceAll("(?<=\\S) +(?!\\n|$| )", " ");
        if (print) {
            System.out.println("preprocessing result: -----------");
            System.out.println(resultString);
            System.out.println("---------------------------------\n");
        }
        return resultString;
    }

}
