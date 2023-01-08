package assign1.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NGramSequence {
    public static Map<String, Long> getFrequencyDistribution(ArrayList<String> words, int nGram) {
        List<String> symbols = new ArrayList<>();
        words.stream()
                .forEach((word) -> {
                    IntStream.range(0, word.length() - nGram + 1)
                            .forEach((counter) -> symbols.add(word.substring(counter, counter + nGram)));
                });

        Map<String, Long> ourMap = symbols.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return ourMap;
    }

}
