package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Reader {

    public static void readFiles() throws IOException {
        // TODO: make path relative & do fixes related to code readability
        File directoryPath = new File("C:\\Users\\User\\Downloads\\adv-java-assignment1-main\\adv-java-assignment1-main\\Language");
        LanguageContent mysteryFile = new LanguageContent("mystery", directoryPath);
        List<File> allFiles = Arrays.asList(directoryPath.listFiles());
        ArrayList<LanguageContent> allLanguages = new ArrayList<>();

        allFiles.stream().forEach(file -> {
            if (file.isDirectory()) {
                String language = file.getName().substring(file.getName().length() - 2).toUpperCase();
                LanguageContent languageContent = new LanguageContent(language, file);
                allLanguages.add(languageContent);
            }
        });
    }
}
