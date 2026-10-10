package revision.class_problems;

public class WordReverserPalindrome {

    static void checkWord(String word) {
        String reversed = new StringBuilder(word).reverse().toString();   // mutable builder does the reversing

        if (reversed.equals(word)) {
            System.out.println(reversed + " - palindrome");
        } else {
            System.out.println(reversed + " - not a palindrome");
        }
    }

    public static void main(String[] args) {
        checkWord("level");
        checkWord("java");
    }
}