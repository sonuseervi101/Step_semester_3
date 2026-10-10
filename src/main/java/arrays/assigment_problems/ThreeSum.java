package arrays.assigment_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    // Returns all unique triplets that sum to 0
    public static int[][] threeSum(int[] nums) {
        int[] sorted = nums.clone();   // copy, so the original array isn't changed
        Arrays.sort(sorted);

        List<int[]> triplets = new ArrayList<>();
        int n = sorted.length;

        for (int i = 0; i < n - 2; i++) {
            // Skip duplicate values for the first number
            if (i > 0 && sorted[i] == sorted[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = sorted[i] + sorted[left] + sorted[right];

                if (sum == 0) {
                    triplets.add(new int[]{sorted[i], sorted[left], sorted[right]});
                    left++;
                    right--;

                    // Skip duplicate values for the second and third numbers
                    while (left < right && sorted[left] == sorted[left - 1]) {
                        left++;
                    }
                    while (left < right && sorted[right] == sorted[right + 1]) {
                        right--;
                    }
                } else if (sum < 0) {
                    left++;    // sum too small: move left pointer to a bigger number
                } else {
                    right--;   // sum too big: move right pointer to a smaller number
                }
            }
        }

        return triplets.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        System.out.println(Arrays.toString(nums1) + " -> " + Arrays.deepToString(threeSum(nums1)));

        int[] nums2 = {0, 0, 0};
        System.out.println(Arrays.toString(nums2) + " -> " + Arrays.deepToString(threeSum(nums2)));

        // Extra test with many repeated values, as the PDF recommends
        int[] nums3 = {-2, 0, 0, 2, 2, -2, 0};
        System.out.println(Arrays.toString(nums3) + " -> " + Arrays.deepToString(threeSum(nums3)));
    }
}