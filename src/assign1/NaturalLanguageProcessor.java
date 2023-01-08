package assign1;

import assign1.Utils.DataReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class NaturalLanguageProcessor {
    private static LanguageModel mysteryFile;
    private static ArrayList<LanguageModel> languageModels;

    public static void process(String path, int nGramIndex) throws IOException {
        languageModels = DataReader.readLanguageDirectories(path, nGramIndex);
        mysteryFile = DataReader.readMysteryFile(path + "/mystery.txt", nGramIndex);
        calculateDocumentDistances();
    }

    public static void calculateDocumentDistances() {
        Map<String, Double> documentDistances = new TreeMap<>();
        languageModels.forEach((languageModel -> {
            AtomicLong sum = new AtomicLong(0);
            languageModel.getLanguageMap().forEach((nGram, count) -> {
                if (mysteryFile.getLanguageMap().containsKey(nGram)) {
                    sum.addAndGet(count * mysteryFile.getLanguageMap().get(nGram));
                }
            });
            double S = sum.get() / (mysteryFile.getRootOfSquares() * languageModel.getRootOfSquares());
            documentDistances.put(languageModel.getLanguage(), Math.toDegrees(Math.acos(S)));
        }));
        System.out.println();

        documentDistances.values().stream().sorted();

        Map<String, Double> sortedMap = documentDistances.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1));
        System.out.println("Printing from the most similar language, to the least one:");
        System.out.println("-----------------------------------------------------------");
        sortedMap.forEach((language, similarity) -> {
            System.out.println("Language: " + language);
            System.out.println("Degrees:" + similarity);
        });
    }
}
