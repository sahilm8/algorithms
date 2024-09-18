package algorithms.sorting;

import java.util.Arrays;

/*
 * Repeatedly steps through the list, compares adjacent elements
 * and swaps them if they're in the wrong order.
 */
public class BubbleSort {
    /*
     * The arr.length - i - 1 condition ensures that we don't
     * compare beyond the end of the array and that we don't
     * re-compare elements that have already been sorted
     * in previous iterations.
     */
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 4, 1, 2, 3, -1 };
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}