package arrays.class_problems;

import java.util.Arrays;

public class MergeSortedArrays {

    // Merges two sorted arrays into one sorted array
    public static int[] mergeSortedArrays(int[] arr1, int[] arr2) {
        int[] result = new int[arr1.length + arr2.length];

        int i = 0;   // pointer for arr1
        int j = 0;   // pointer for arr2
        int k = 0;   // pointer for result

        // Compare the current elements and copy the smaller one
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] <= arr2[j]) {
                result[k] = arr1[i];
                i++;
            } else {
                result[k] = arr2[j];
                j++;
            }
            k++;
        }

        // Copy whatever is left in arr1
        while (i < arr1.length) {
            result[k] = arr1[i];
            i++;
            k++;
        }

        // Copy whatever is left in arr2
        while (j < arr2.length) {
            result[k] = arr2[j];
            j++;
            k++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] a1 = {1, 3, 5};
        int[] b1 = {2, 4, 6};
        System.out.println(Arrays.toString(a1) + " + " + Arrays.toString(b1) + " -> "
                + Arrays.toString(mergeSortedArrays(a1, b1)));

        int[] a2 = {};
        int[] b2 = {1, 2, 3};
        System.out.println(Arrays.toString(a2) + " + " + Arrays.toString(b2) + " -> "
                + Arrays.toString(mergeSortedArrays(a2, b2)));
    }
}