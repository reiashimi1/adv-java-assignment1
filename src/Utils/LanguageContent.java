package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.function.Function;

public class LanguageContent {
    private String language;
    private static File langDirectory;
    private int nGram = 2;
    public ArrayList<String> words = new ArrayList<>();

    public LanguageContent(String language, File directory) {
        this.language = language;
        this.langDirectory = directory;
        process();
        System.out.println(words);
        getGrams();
    }

    public LanguageContent(String language, File directory, int nGram) {
        this.language = language;
        this.langDirectory = directory;
        this.nGram = nGram;
        process();
        System.out.println(words);
        getGrams();
    }

    public void getGrams() {
        List<String> symbols = new ArrayList<>();
        words.stream()
                .forEach((word) -> {
                    IntStream.range(0, word.length() - nGram + 1)
                            .forEach((counter) -> symbols.add(word.substring(counter, counter + nGram)));
                });

        Map<String, Long> ourMap = symbols.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(ourMap);
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
                String content = removePunctuation(line);
                Arrays.stream(content.split("\\s+"))
                        .forEach((contentWord) -> words.add(contentWord));
            });
        }
    }

    public static String removePunctuation(String content) {
        String cleanContent = content.replaceAll("[!\\\"#\\＄%&\\'\\(\\)\\*\\+,-\\./:;<=>\\?@\\[\\\\\\]\\^_`{\\|}~]", " ");

        return cleanContent;
    }
}
