package basics.assigment_problems;

public class TypingAccuracyChecker {

    // Compares the typed text with the original, character by character
    public static void checkTypingAccuracy(String original, String typed) {
        if (original.length() != typed.length()) {
            System.out.println("Error: both texts must be the same length");
            return;
        }

        int matched = 0;
        int firstMismatch = -1;   // -1 means "no mismatch found yet"

        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;   // remember only the FIRST mismatch
            }
        }

        double accuracy = matched * 100.0 / original.length();

        System.out.print("Matched: " + matched + "/" + original.length()
                + " | Accuracy: " + String.format("%.2f", accuracy) + "%");

        if (firstMismatch == -1) {
            System.out.println(" | No Mismatches");
        } else {
            System.out.println(" | First Mismatch at position " + (firstMismatch + 1)
                    + " ('" + original.charAt(firstMismatch) + "' vs '" + typed.charAt(firstMismatch) + "')");
        }
    }

    public static void main(String[] args) {
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");
    }
}