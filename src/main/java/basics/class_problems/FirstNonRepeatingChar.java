package basics.class_problems;

public class FirstNonRepeatingChar {

    // Returns the first character that appears only once, or '\0' if none
    public static char findFirstNonRepeatingChar(String text) {
        int[] count = new int[256];   // one counter for every possible character

        // Step 1: count how many times each character appears
        for (int i = 0; i < text.length(); i++) {
            count[text.charAt(i)]++;
        }

        // Step 2: scan left to right and return the first one with count 1
        for (int i = 0; i < text.length(); i++) {
            if (count[text.charAt(i)] == 1) {
                return text.charAt(i);
            }
        }

        return '\0';   // means "no such character"
    }

    public static void main(String[] args) {
        String[] inputs = {"swiss", "aabbcc"};

        for (String text : inputs) {
            char result = findFirstNonRepeatingChar(text);

            System.out.print("Input: \"" + text + "\" -> ");
            if (result == '\0') {
                System.out.println("No Non-Repeating Character Found");
            } else {
                System.out.println("First Non-Repeating Character: '" + result + "'");
            }
        }
    }
}