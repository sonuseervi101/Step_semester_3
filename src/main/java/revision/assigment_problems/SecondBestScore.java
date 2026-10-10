package revision.assigment_problems;

import java.util.Arrays;

/*
 * Complexity:
 * - This solution: O(n) time, O(1) space. One pass with two variables.
 * - Sorting first: O(n log n) time (plus O(n) extra space if the original order must be kept),
 *   and you would still need a backwards scan to skip duplicates of the highest.
 *   The single pass is faster and uses no extra memory.
 */
public class SecondBestScore {

    static int secondHighest(int[] scores) {
        int highest = -1;   // scores are 0..100, so -1 means "not found yet"
        int second = -1;

        for (int score : scores) {
            if (score > highest) {
                second = highest;      // old highest drops to second
                highest = score;
            } else if (score < highest && score > second) {
                second = score;        // new second, strictly below the highest
            }
            // score == highest: skipped, because it is not a DIFFERENT score
        }

        return second;
    }

    public static void main(String[] args) {
        int[] sample1 = {45, 78, 92, 78, 60};
        System.out.println(Arrays.toString(sample1) + " -> " + secondHighest(sample1));

        int[] sample2 = {50, 50, 50};
        System.out.println(Arrays.toString(sample2) + " -> " + secondHighest(sample2));
    }
}