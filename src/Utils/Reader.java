package Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Reader {

    public static void main(String[] args) {
        try {
            readFiles();
        } catch (IOException exception) {
            System.out.println("Error");
        }
    }

    public static void readFiles() throws IOException {
        // TODO: make path relative & do fixes related to code readability
        File directoryPath = new File("C:/Users/user/Desktop/Advanced Java/Code/Assignment1/Language");
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

    public static String getFileContent(File filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String content = reader.readLine();
            content = removePunctuation(content);
            return content;
        }
    }

    public static String removePunctuation(String content) {
        String cleanContent = content.replaceAll("[!\\\"#\\＄%&\\'\\(\\)\\*\\+,-\\./:;<=>\\?@\\[\\\\\\]\\^_`{\\|}~]", " ");

        return cleanContent;
    }

    // Function for getting .txt files from a directory
    public static ArrayList<File> getFilesForEachDir(File directory) {
        ArrayList<File> dirFiles = new ArrayList<>();
        Arrays.asList(directory.listFiles()).stream()
                .forEach((nestedFile) -> {
                    if (nestedFile.getName().endsWith(".txt")) {
                        dirFiles.add(nestedFile);
                    }
                });

        return dirFiles;
    }
}
