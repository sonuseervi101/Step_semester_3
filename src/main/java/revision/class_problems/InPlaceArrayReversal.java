package revision.class_problems;

import java.util.Arrays;

public class InPlaceArrayReversal {

    // Reverses the array directly, without creating a second array
    static void reverseInPlace(int[] tokens) {
        int left = 0;
        int right = tokens.length - 1;

        while (left < right) {
            int temp = tokens[left];      // swap the two ends
            tokens[left] = tokens[right];
            tokens[right] = temp;

            left++;                       // move both pointers toward the middle
            right--;
        }
    }

    public static void main(String[] args) {
        int[] tokens = {11, 22, 33, 44};
        reverseInPlace(tokens);
        System.out.println(Arrays.toString(tokens));
    }
}