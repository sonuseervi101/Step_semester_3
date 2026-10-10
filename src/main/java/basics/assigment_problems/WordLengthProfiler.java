package basics.assigment_problems;

public class WordLengthProfiler {

    // Counts Short, Medium and Long words in a review
    public static void classifyWordLengths(String review) {
        String[] words = review.trim().split("\\s+");   // split on one or more spaces

        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        for (String word : words) {
            String lettersOnly = word.replaceAll("[^a-zA-Z]", "");   // remove punctuation
            int length = lettersOnly.length();

            if (length == 0) {
                continue;   // skip things like "!!!" that have no letters
            }

            if (length <= 4) {
                shortCount++;
            } else if (length <= 8) {
                mediumCount++;
            } else {
                longCount++;
            }
        }

        System.out.println("Short: " + shortCount + " | Medium: " + mediumCount + " | Long: " + longCount);
    }

    public static void main(String[] args) {
        String review = "This movie was absolutely fantastic and thrilling";
        System.out.println("Review: \"" + review + "\"");
        classifyWordLengths(review);
    }
}