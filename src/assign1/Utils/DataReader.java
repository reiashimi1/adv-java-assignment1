package assign1.Utils;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DataReader {

    public static ArrayList<LanguageModel> readLanguageDirectories(String path, int nGramIndex) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        File directory = new File(path);
        List<File> languageDirectories = Arrays.asList(directory.listFiles());
        ArrayList<LanguageModel> allLanguages = new ArrayList<>();

        languageDirectories.stream().forEach(file -> {
            //TODO tn ktu duhet rregullu besoj
                executorService.execute(() -> {
                    if(file.isDirectory()) {
                        String language = file.getName().substring(file.getName().length() - 2).toUpperCase();
                        LanguageModel languageModel = new LanguageModel(language, file, nGramIndex);
                        allLanguages.add(languageModel);
                    }
                });

                executorService.shutdown();

                try{
                    executorService.awaitTermination(1, TimeUnit.MINUTES);
                }
                catch(InterruptedException e){
                    System.out.println("Thread was interrupted!");
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
