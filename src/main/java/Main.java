import ast_tree.AstTreeGenerator;
import code_generation.RandomCodeGenerator;
import mutation.Operator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            AstTreeGenerator.getTreeParent();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // parse tree

        // choose part

        // generate new code for chosen part

        // regenerate code from tree

        System.out.println();
    }
}
