package arrays.assigment_problems;

import java.util.Arrays;

public class ProductExceptSelf {

    // answer[i] = product of every element except nums[i], without division
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // Pass 1 (left to right): answer[i] = product of everything LEFT of i
        answer[0] = 1;   // nothing to the left of the first element
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        // Pass 2 (right to left): multiply in the product of everything RIGHT of i
        int rightProduct = 1;   // nothing to the right of the last element
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = answer[i] * rightProduct;
            rightProduct = rightProduct * nums[i];
        }

        return answer;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 4};
        System.out.println(Arrays.toString(nums1) + " -> " + Arrays.toString(productExceptSelf(nums1)));

        int[] nums2 = {-1, 1, 0, -3, 3};
        System.out.println(Arrays.toString(nums2) + " -> " + Arrays.toString(productExceptSelf(nums2)));
    }
}