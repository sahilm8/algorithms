package algorithms.design;

import java.util.Arrays;

/*
 * The two-pointer technique is an algorithm design pattern
 * that uses two pointers to solve problems efficiently,
 * often reducing time complexity from O(n^2) to O(n).
 * It's commonly used for problems involving arrays or linked lists,
 * especially when dealing with sorted data or when searching
 * for pairs with certain properties.
 * 
 * This two-pointer implementation demonstrates its use to find a pair
 * of numbers in an unsorted array that sum up to a target value:
 */
public class TwoPointer {
    public static int[] findPair(int[] arr, int target) {
        Arrays.sort(arr);  // sort first
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                return new int[] {arr[left], arr[right]};
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[] {};
    }

    public static void main(String[] args) {
        System.out.println(
                "Looking for two values whose sum is 3: "
                + Arrays.toString(findPair(new int[] { 2, 4, 1, 3 }, 3))
            );
    }
}