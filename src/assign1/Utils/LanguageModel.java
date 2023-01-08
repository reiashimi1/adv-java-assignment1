package assign1.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class LanguageModel {
    private String language;
    private static File path;
    public ArrayList<String> words = new ArrayList<>();
    Map<String, Long> languageMap;
    double rootOfSquares;

    public LanguageModel(String language, File directory, int nGram) {
        setLanguage(language);
        setPath(directory);
        processDirectory();
        Map<String, Long> langMap = NGramSequence.getFrequencyDistribution(words, nGram);
        setLanguageMap(langMap);
        calculateRootOfSquares();
    }

    private void processDirectory() {
        if (path.isDirectory()) {
            getDirectoryContent();
            return;
        }
        // differentiate from language directories and mystery file
        try {
            getFileContent(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<File> getDirectoryContent() {
        ArrayList<File> dirFiles = new ArrayList<>();
        Arrays.asList(path.listFiles()).stream()
                .forEach((nestedFile) -> {
                    if (nestedFile.getName().endsWith(".txt")) {
                        dirFiles.add(nestedFile);
                    }
                });

        dirFiles.stream()
                .forEach(file -> {
                    try {
                        getFileContent(file);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
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

    public void calculateRootOfSquares() {
        AtomicLong squares = new AtomicLong();
        languageMap.values().stream()
                .forEach(value -> {
                    squares.addAndGet((long) Math.pow(value, 2));
                });
        rootOfSquares = Math.sqrt(squares.get());
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public static File getPath() {
        return path;
    }

    public static void setPath(File path) {
        LanguageModel.path = path;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }

    public Map<String, Long> getLanguageMap() {
        return languageMap;
    }

    public void setLanguageMap(Map<String, Long> languageMap) {
        this.languageMap = languageMap;
    }

    public double getRootOfSquares() {
        return rootOfSquares;
    }

    public void setRootOfSquares(double rootOfSquares) {
        this.rootOfSquares = rootOfSquares;
    }
}
