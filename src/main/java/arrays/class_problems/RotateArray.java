package arrays.class_problems;

import java.util.Arrays;

public class RotateArray {

    // Rotates the array to the right by k positions
    public static int[] rotateArray(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return nums;   // nothing to rotate (also avoids dividing by zero below)
        }

        k = k % n;   // rotating by n (or a multiple of n) changes nothing

        int[] rotated = new int[n];
        for (int i = 0; i < n; i++) {
            rotated[(i + k) % n] = nums[i];
        }

        return rotated;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(nums1) + ", k = 3 -> " + Arrays.toString(rotateArray(nums1, 3)));

        int[] nums2 = {1, 2};
        System.out.println(Arrays.toString(nums2) + ", k = 3 -> " + Arrays.toString(rotateArray(nums2, 3)));
    }
}