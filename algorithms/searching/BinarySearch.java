package algorithms.searching;

/*
 * Binary search is an efficient algorithm for searching
 * a sorted array by repeatedly dividing the search interval
 * in half. It works by comparing the target value to the
 * middle element of the array. If they are not equal,
 * the half in which the target cannot lie is eliminated,
 * and the search continues on the remaining half until the
 * target is found or it's clear the target is not in the array.
 */
public class BinarySearch {
    public int binarySearch(int[] arr, int target) {
        int l = 0;
        int r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] == target) {
                return m;
            }
            if (target > arr[m]) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        System.out.println("Looking for 3: "
                + bs.binarySearch(new int[] { 1, 2, 3, 4, 5, 6 }, 3));
    }
}