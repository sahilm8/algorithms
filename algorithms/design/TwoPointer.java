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
 * of numbers in a sorted array that sum up to a target value:
 */
public class TwoPointer {
    public static int[] twoPointer(int[] arr, int target) {
        int left = 0, right = arr.length - 1; // two pointers
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
                "Looking for two values whose sum is 4: " + Arrays.toString(twoPointer(new int[] { 1, 2, 3, 4 }, 4)));
    }
}
