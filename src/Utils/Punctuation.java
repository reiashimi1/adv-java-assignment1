package Utils;

public class Punctuation {

    public static String removePunctuation(String content) {
//        String cleanContent = content.replaceAll("[!\\\"#\\＄%&\\'\\(\\)\\*\\+,-\\./:;<=>\\?@\\[\\\\\\]\\^_`{\\|}~]", " ");
        String cleanContent = content.replaceAll("\\p{Punct}", " ");
        return cleanContent;
    }
}
