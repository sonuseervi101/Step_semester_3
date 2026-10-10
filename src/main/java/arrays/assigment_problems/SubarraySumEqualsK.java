package arrays.assigment_problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

    // Counts contiguous subarrays whose sum is exactly k
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);   // the "empty prefix": sum 0 has been seen once, before we start

        int currentSum = 0;
        int count = 0;

        for (int num : nums) {
            currentSum = currentSum + num;

            // How many earlier prefixes had sum (currentSum - k)?
            // Each one marks the start of a subarray that ends here and sums to k.
            count = count + prefixCount.getOrDefault(currentSum - k, 0);

            // Record this prefix sum for future positions
            prefixCount.put(currentSum, prefixCount.getOrDefault(currentSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1};
        System.out.println(Arrays.toString(nums1) + ", k = 2 -> " + subarraySum(nums1, 2));

        int[] nums2 = {1, -1, 0};
        System.out.println(Arrays.toString(nums2) + ", k = 0 -> " + subarraySum(nums2, 0));
    }
}