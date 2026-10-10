package methods.class_problems;

public class SeatingGridOptimizer {

    // Helper: computes the average of one row, nothing else
    private static double rowAverage(int[] row) {
        if (row.length == 0) {
            return 0;   // avoid dividing by zero on an empty row
        }

        int sum = 0;
        for (int value : row) {
            sum = sum + value;
        }
        return (double) sum / row.length;
    }

    // Classifies every row as Quiet Zone or Buzzing Zone
    static String classifyRows(int[][] seatingScores, int threshold) {
        StringBuilder result = new StringBuilder();

        for (int r = 0; r < seatingScores.length; r++) {
            double average = rowAverage(seatingScores[r]);   // called once per row

            String zone;
            if (average < threshold) {
                zone = "Quiet Zone";
            } else {
                zone = "Buzzing Zone";
            }

            result.append("Row ").append(r).append(": ").append(zone);
            if (r < seatingScores.length - 1) {
                result.append(" | ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        int[][] seatingScores = {
                {40, 50, 45},
                {85, 90, 95},
                {30, 20, 25}
        };

        System.out.println(classifyRows(seatingScores, 60));
    }
}
