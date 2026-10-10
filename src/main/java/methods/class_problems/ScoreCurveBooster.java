package methods.class_problems;

import java.util.Arrays;

public class ScoreCurveBooster {

    // Adds the bonus to every score directly inside the caller's array
    static void curveScores(int[] scores, int bonus) {
        for (int i = 0; i < scores.length; i++) {
            scores[i] = scores[i] + bonus;
        }
    }

    public static void main(String[] args) {
        int[] scores = {70, 85, 60};
        System.out.println("Before: " + Arrays.toString(scores));

        curveScores(scores, 10);   // no return value, no reassignment

        System.out.println("After:  " + Arrays.toString(scores));
    }
}