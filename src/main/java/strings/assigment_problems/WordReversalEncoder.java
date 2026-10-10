package strings.assigment_problems;

public class WordReversalEncoder {

    // Reverses each word but keeps the word order the same
    public static String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int w = 0; w < words.length; w++) {
            String word = words[w];

            // Build the reverse of this word, letter by letter
            StringBuilder reversedWord = new StringBuilder();
            for (int i = word.length() - 1; i >= 0; i--) {
                reversedWord.append(word.charAt(i));
            }

            result.append(reversedWord);

            // Add a space between words, but not after the last one
            if (w < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String sentence = "hello club";
        System.out.println("\"" + sentence + "\" -> " + reverseEachWord(sentence));
    }
}