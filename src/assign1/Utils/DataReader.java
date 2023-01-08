package assign1.Utils;

import java.io.File;
import java.util.*;

public class DataReader {

    public static ArrayList<LanguageModel> readLanguageDirectories(String path, int nGramIndex) {
        File directory = new File(path);
        List<File> languageDirectories = Arrays.asList(directory.listFiles());
        ArrayList<LanguageModel> allLanguages = new ArrayList<>();

        languageDirectories.stream().forEach(file -> {
            if (file.isDirectory()) {
                String language = file.getName().substring(file.getName().length() - 2).toUpperCase();
                LanguageModel languageModel = new LanguageModel(language, file, nGramIndex);
                allLanguages.add(languageModel);
            }
        });

        return allLanguages;
    }

    public static LanguageModel readMysteryFile(String path, int nGramIndex) {
        File directoryPath = new File(path);
        LanguageModel mysteryFile = new LanguageModel("", directoryPath, nGramIndex);

        return mysteryFile;
    }
}
