package arrays.assigment_problems;

import java.util.Arrays;

public class FindMinRotatedArray {

    // Modified binary search: finds the minimum in O(log n)
    public static int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                // Middle is bigger than the right end, so the "drop" (minimum)
                // must be somewhere to the RIGHT of mid
                left = mid + 1;
            } else {
                // Middle is smaller than the right end, so mid..right is sorted.
                // The minimum is at mid or to its LEFT
                right = mid;
            }
        }

        return nums[left];   // left and right meet at the minimum
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println(Arrays.toString(nums1) + " -> " + findMin(nums1));

        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println(Arrays.toString(nums2) + " -> " + findMin(nums2));

        int[] nums3 = {11, 13, 15, 17};
        System.out.println(Arrays.toString(nums3) + " -> " + findMin(nums3));
    }
}