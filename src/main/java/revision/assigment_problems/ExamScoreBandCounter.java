package revision.assigment_problems;

/*
 * Complexity:
 * - Linear scan: O(n) time, O(1) space. Checks every score, too slow for 10^6 scores queried often.
 * - This solution: O(log n) time, O(1) space. Two binary searches, each halving the range every step.
 *
 * Duplicates at the edges: a standard binary search stops at ANY matching element, which could be
 * in the middle of a run of duplicates (e.g. any of the three 58s). To count correctly we need the
 * FIRST position with score >= low and the FIRST position with score > high. So when we find a
 * match we don't stop: we record it and keep searching to the LEFT for an earlier one.
 */
public class ExamScoreBandCounter {

    // Returns the first index whose score is >= target (or scores.length if none)
    static int firstIndexAtLeast(int[] scores, int target) {
        int left = 0;
        int right = scores.length;   // "right" is one past the end: the answer could be "none"

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] >= target) {
                right = mid;         // mid might be the answer: keep it, search the left half
            } else {
                left = mid + 1;      // mid is too small: the answer is to the right
            }
        }
        return left;
    }

    static int countInBand(int[] scores, int low, int high) {
        int start = firstIndexAtLeast(scores, low);       // first score >= low
        int end = firstIndexAtLeast(scores, high + 1);    // first score > high (scores are integers)
        return end - start;
    }

    public static void main(String[] args) {
        int[] scores = {35, 42, 42, 50, 58, 58, 58, 63, 71, 88};

        System.out.println("low = 42, high = 58  -> " + countInBand(scores, 42, 58));
        System.out.println("low = 90, high = 100 -> " + countInBand(scores, 90, 100));
    }
}