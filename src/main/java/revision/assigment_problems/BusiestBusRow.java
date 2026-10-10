package revision.assigment_problems;

/*
 * Complexity: O(r * c) time, since every seat in every row is visited once.
 * Additional space: O(1), only a few variables (current total, best row, best total).
 */
public class BusiestBusRow {

    // Returns {bestRowIndex, bestTotal}
    static int[] busiestRow(int[][] grid) {
        int bestRow = 0;
        int bestTotal = -1;   // seat counts are never negative, so any real row beats this

        for (int r = 0; r < grid.length; r++) {
            int rowTotal = 0;
            for (int c = 0; c < grid[r].length; c++) {
                rowTotal = rowTotal + grid[r][c];
            }

            if (rowTotal > bestTotal) {   // strictly larger: on a tie, the earlier row is kept
                bestTotal = rowTotal;
                bestRow = r;
            }
        }

        return new int[]{bestRow, bestTotal};
    }

    public static void main(String[] args) {
        int[][] grid = {
                {2, 0, 1},
                {3, 3, 1},
                {1, 1, 1}
        };

        int[] result = busiestRow(grid);
        System.out.println("Row " + result[0] + ", Total " + result[1]);
    }
}