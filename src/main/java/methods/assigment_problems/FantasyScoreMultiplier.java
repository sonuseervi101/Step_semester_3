package methods.assigment_problems;

import java.util.Arrays;

public class FantasyScoreMultiplier {

    // Doubles the captain's score and multiplies the vice-captain's by 1.5, in place
    static void applyMultipliers(double[] playerScores, int captainIndex, int viceCaptainIndex) {
        playerScores[captainIndex] = playerScores[captainIndex] * 2;
        playerScores[viceCaptainIndex] = playerScores[viceCaptainIndex] * 1.5;
    }

    public static void main(String[] args) {
        double[] scores = {40, 55, 30, 62};
        System.out.println("Before: " + Arrays.toString(scores));

        applyMultipliers(scores, 1, 3);

        System.out.println("After:  " + Arrays.toString(scores));
    }
}