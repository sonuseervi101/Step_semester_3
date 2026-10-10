package basics.class_problems;

public class PalindromeChecker {

    // Approach 1: Iterative - compare from both ends moving to the middle
    public static boolean isPalindromeIterative(String text) {
        int left = 0;
        int right = text.length() - 1;

        while (left < right) {
            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Approach 2: Recursive - compare first and last, then check the middle part
    public static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) {
            return true;
        }
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    // Approach 3: Array reversal - reverse the characters and compare
    public static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];

        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }

        String reversedText = new String(reversed);
        return reversedText.equals(text);
    }

    // Turns true/false into a readable word
    public static String result(boolean isPalindrome) {
        if (isPalindrome) {
            return "Palindrome";
        }
        return "Not Palindrome";
    }

    public static void main(String[] args) {
        String[] words = {"madam", "hello"};

        for (String word : words) {
            System.out.println("Input: \"" + word + "\"");
            System.out.println("Iterative: " + result(isPalindromeIterative(word))
                    + " | Recursive: " + result(isPalindromeRecursive(word))
                    + " | Array Reversal: " + result(isPalindromeArrayReversal(word)));
            System.out.println();
        }
    }
}