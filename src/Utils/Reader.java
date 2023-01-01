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
        File mysteryFile = new File(directoryPath + "/mystery.txt");
        List<File> allFiles = Arrays.asList(directoryPath.listFiles());

        String mysteryFileContent = getFileContent(mysteryFile);

        Map<String, ArrayList<File>> filesForLanguage = new TreeMap<>();

        allFiles.stream().forEach(file -> {
            if (file.isDirectory()) {
                String language = file.getName().substring(file.getName().length() - 2).toUpperCase();
                ArrayList<File> directoryFiles = getFilesForEachDir(file);
                filesForLanguage.put(language, directoryFiles);
            }
        });

        filesForLanguage.forEach((folderName, files) -> {
            System.out.println(folderName);
            System.out.println("------------------");
            files.stream()
                    .forEach(file -> {
                        try {
                            System.out.println(getFileContent(file));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
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
        String cleanContent = content.replaceAll("[^A-Za-z0-9_]", " ");

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
