package Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NGrams {

    private int nGram = 2;
    public ArrayList<String> words;

    public NGrams(ArrayList<String> words){
        this.words = words;
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

}
