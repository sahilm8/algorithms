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
    public int[] twoPointer(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        while (l < r) {
            int sum = arr[l] + arr[r];
            if (sum == target) {
                return new int[] { arr[l], arr[r] };
            }
            if (sum < target) {
                l++;
            } else {
                r--;
            }
        }
        return new int[] {};
    }

    public static void main(String[] args) {
        TwoPointer tp = new TwoPointer();
        System.out.println(
                "Looking two values whose sum is 4: " + Arrays.toString(tp.twoPointer(new int[] { 1, 2, 3, 4 }, 4)));
    }
}
