package revision.assigment_problems;

import java.util.Arrays;

/*
 * Complexity: O(n) time, O(n) space (for the new roster array).
 *
 * Why rotating one place at a time, k times, is slow:
 * each single rotation moves all n names, so k rotations cost O(n * k).
 * With k up to 10^9 and n = 100, that is 10^11 steps, far too slow.
 * Using k % n first, then placing each name directly at (i + k) % n, needs only n steps,
 * no matter how big k is.
 */
public class DutyRosterRotation {

    static String[] rotateRoster(String[] names, int k) {
        int n = names.length;
        int shift = k % n;   // remove full turns: rotating by n changes nothing

        String[] rotated = new String[n];
        for (int i = 0; i < n; i++) {
            rotated[(i + shift) % n] = names[i];
        }
        return rotated;
    }

    public static void main(String[] args) {
        String[] names = {"A", "B", "C", "D", "E"};

        System.out.println("k = 2 -> " + Arrays.toString(rotateRoster(names, 2)));
        System.out.println("k = 7 -> " + Arrays.toString(rotateRoster(names, 7)));
    }
}