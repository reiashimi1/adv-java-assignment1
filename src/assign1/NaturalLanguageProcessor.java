package assign1;

import assign1.Utils.LanguageModel;
import assign1.Utils.DataReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

public class NaturalLanguageProcessor {
    private static LanguageModel mysteryFile;
    private static ArrayList<LanguageModel> languageModels;

    public static void process(String path, int nGramIndex) throws IOException {
        languageModels = DataReader.readLanguageDirectories(path, nGramIndex);
        mysteryFile = DataReader.readMysteryFile(path + "/mystery.txt", nGramIndex);
        calculateDocumentDistances();
    }

    public static void calculateDocumentDistances() {
        languageModels.forEach((languageModel -> {
            AtomicLong sum = new AtomicLong(0);
            languageModel.getLanguageMap().forEach((nGram, count) -> {
                if (mysteryFile.getLanguageMap().containsKey(nGram)) {
                    sum.addAndGet(count * mysteryFile.getLanguageMap().get(nGram));
                }
            });
            System.out.println(languageModel.getLanguage() + ": " + sum);
            System.out.println(Math.cos(sum.get() / (mysteryFile.getRootOfSquares() * languageModel.getRootOfSquares())));
        }));
    }
}
