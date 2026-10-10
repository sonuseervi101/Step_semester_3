package methods.assigment_problems;

public class MatchDayGridAnalyzer {

    // Helper: computes the average of one match's runs, nothing else
    private static double rowAverage(int[] row) {
        if (row.length == 0) {
            return 0;   // avoid dividing by zero on an empty row
        }

        int sum = 0;
        for (int runs : row) {
            sum = sum + runs;
        }
        return (double) sum / row.length;
    }

    // Classifies every match as Power Surge or Normal
    static String classifyMatches(int[][] runsPerOver, int threshold) {
        StringBuilder result = new StringBuilder();

        for (int m = 0; m < runsPerOver.length; m++) {
            double average = rowAverage(runsPerOver[m]);   // called once per match

            String label;
            if (average >= threshold) {
                label = "Power Surge";
            } else {
                label = "Normal";
            }

            result.append("Match ").append(m).append(": ").append(label);
            if (m < runsPerOver.length - 1) {
                result.append(" | ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        int[][] runsPerOver = {
                {4, 6, 8},
                {10, 12, 14},
                {2, 3, 1}
        };

        System.out.println(classifyMatches(runsPerOver, 8));
    }
}