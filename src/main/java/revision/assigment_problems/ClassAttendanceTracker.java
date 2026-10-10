package revision.assigment_problems;

import java.util.Arrays;

/*
 * Complexity: O(n) time, O(1) space.
 * Yes, both answers are found in a SINGLE pass: on each day we update the present count
 * and the running streak at the same time.
 */
public class ClassAttendanceTracker {

    // Returns {presentDays, longestStreak}
    static int[] attendanceSummary(int[] days) {
        int present = 0;
        int currentStreak = 0;
        int longestStreak = 0;

        for (int day : days) {
            if (day == 1) {
                present++;
                currentStreak++;                         // streak grows on every 1
                if (currentStreak > longestStreak) {
                    longestStreak = currentStreak;       // remember the largest value it reaches
                }
            } else {
                currentStreak = 0;                       // streak resets on every 0
            }
        }

        return new int[]{present, longestStreak};
    }

    public static void main(String[] args) {
        int[][] samples = {
                {1, 1, 0, 1, 1, 1, 0, 1},
                {0, 0, 0}
        };

        for (int[] days : samples) {
            int[] result = attendanceSummary(days);
            System.out.println(Arrays.toString(days) + " -> Present: " + result[0]
                    + ", Longest streak: " + result[1]);
        }
    }
}