package mutation;

import code_generator.RandomCodeGenerator;

public class Number implements Mutation{

    @Override
    public String mutate() {
        String grammar = "grammar";
        return RandomCodeGenerator.generate(grammar);
    }
}
