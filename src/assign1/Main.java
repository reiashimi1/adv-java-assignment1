package assign1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String defaultPath = "C:/Users/user/Desktop/Advanced Java/Code/Assignment1/Language";
        int defaultNGramSequence = 2;

        try {
            if (args.length < 1) {
                NaturalLanguageProcessor.process(defaultPath, defaultNGramSequence);
            } else if (args.length < 2) {
                NaturalLanguageProcessor.process(args[0], defaultNGramSequence);
            } else {
                NaturalLanguageProcessor.process(args[0], Integer.parseInt(args[1]));
            }
        } catch (IOException exception) {
            System.out.println("Error");
        }
    }
}
