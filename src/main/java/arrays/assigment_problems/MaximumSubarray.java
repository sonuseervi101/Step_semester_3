package arrays.assigment_problems;

import java.util.Arrays;

public class MaximumSubarray {

    // Kadane's algorithm: largest sum of any contiguous subarray
    public static int maxSubArray(int[] nums) {
        int currentSum = nums[0];   // best sum of a subarray ending at the current position
        int bestSum = nums[0];      // best sum found anywhere so far

        for (int i = 1; i < nums.length; i++) {
            // Extend the current run, or start fresh from nums[i]: whichever is bigger
            currentSum = Math.max(currentSum + nums[i], nums[i]);

            bestSum = Math.max(bestSum, currentSum);
        }

        return bestSum;
    }

    public static void main(String[] args) {
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(Arrays.toString(nums1) + " -> " + maxSubArray(nums1));

        int[] nums2 = {-3, -1, -2};
        System.out.println(Arrays.toString(nums2) + " -> " + maxSubArray(nums2));
    }
}