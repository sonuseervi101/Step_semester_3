package methods.class_problems;

import java.util.Arrays;

public class TopThreePodium {

    // Finds the top 3 scores in one pass, without sorting
    static int[] findTopThreeScores(int[] scores) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int third = Integer.MIN_VALUE;

        for (int score : scores) {
            if (score > first) {
                // New highest: everyone moves down one place
                third = second;
                second = first;
                first = score;
            } else if (score > second) {
                // New second place: old second moves to third
                third = second;
                second = score;
            } else if (score > third) {
                // New third place
                third = score;
            }
        }

        return new int[]{first, second, third};
    }

    public static void main(String[] args) {
        int[] scores = {45, 82, 79, 90, 33, 90, 61};
        System.out.println(Arrays.toString(scores) + " -> " + Arrays.toString(findTopThreeScores(scores)));
    }
}