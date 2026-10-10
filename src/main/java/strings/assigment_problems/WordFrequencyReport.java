package strings.assigment_problems;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WordFrequencyReport {

    static final String[] STOP_WORDS = {"the", "was", "and", "a", "is", "of", "in"};

    // Checks if a word is in the stop-word list
    public static boolean isStopWord(String word) {
        for (String stopWord : STOP_WORDS) {
            if (stopWord.equals(word)) {
                return true;
            }
        }
        return false;
    }

    public static void printFilteredWordFrequency(String feedback) {
        // Step 1: normalize - lowercase and remove punctuation
        String cleaned = feedback.toLowerCase()
                .replace(".", "")
                .replace(",", "")
                .replace("!", "")
                .replace("?", "")
                .trim();

        // Step 2: split into words
        String[] words = cleaned.split("\\s+");

        // Step 3: count each word, skipping stop words
        Map<String, Integer> frequency = new LinkedHashMap<>();
        for (String word : words) {
            if (word.isEmpty() || isStopWord(word)) {
                continue;
            }
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }

        // Step 4: sort by count, highest first
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(frequency.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());

        // Step 5: print
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        String feedback = "The mentor was great, the session was great and clear.";
        System.out.println("Feedback: \"" + feedback + "\"");
        printFilteredWordFrequency(feedback);
    }
}