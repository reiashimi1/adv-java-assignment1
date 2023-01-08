package assign1.Utils;

public class Punctuation {

    public static String removePunctuation(String content) {
        String cleanContent = content.toLowerCase()
                .replaceAll("\\p{Punct}", " ")
                .replaceAll("\\d", " ")
                .replaceAll("\'", " ");

        return cleanContent;
    }
}
