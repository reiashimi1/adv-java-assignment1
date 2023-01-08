package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.function.Function;

public class LanguageContent {
    private String language;
    private static File langDirectory;
    private int nGram = 2;
    public ArrayList<String> words = new ArrayList<>();

    public NGrams ngram = new NGrams(words);

    public LanguageContent(String language, File directory) {
        this.language = language;
        this.langDirectory = directory;
        process();
        System.out.println(words);
        ngram.getGrams();
    }

    public LanguageContent(String language, File directory, int nGram) {
        this.language = language;
        this.langDirectory = directory;
        this.nGram = nGram;
        process();
        System.out.println(words);
        ngram.getGrams();
    }


    private void process() {
        ArrayList<File> files = getFilesForEachDir();
        files.stream()
                .forEach(file -> {
                    try {
                        getFileContent(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public static ArrayList<File> getFilesForEachDir() {
        ArrayList<File> dirFiles = new ArrayList<>();
        Arrays.asList(langDirectory.listFiles()).stream()
                .forEach((nestedFile) -> {
                    if (nestedFile.getName().endsWith(".txt")) {
                        dirFiles.add(nestedFile);
                    }
                });

        return dirFiles;
    }

    public void getFileContent(File filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.lines().forEach((line) -> {
                String content = Punctuation.removePunctuation(line);
                Arrays.stream(content.split("\\s+"))
                        .forEach((contentWord) -> words.add(contentWord));
            });
        }
    }

}
