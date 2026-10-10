package arrays.class_problems;

import java.util.Arrays;

public class TwoSum {

    // Returns the indices of the two numbers that add up to target
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};   // never happens: the task guarantees one valid pair
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 7, 11, 15};
        System.out.println("nums = " + Arrays.toString(nums1) + ", target = 9 -> "
                + Arrays.toString(twoSum(nums1, 9)));

        int[] nums2 = {3, 2, 4};
        System.out.println("nums = " + Arrays.toString(nums2) + ", target = 6 -> "
                + Arrays.toString(twoSum(nums2, 6)));
    }
}