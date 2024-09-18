package algorithms.sorting;

import java.util.Arrays;

/*
 * Repeatedly steps through the list, compares adjacent elements
 * and swaps them if they're in the wrong order.
 */
public class BubbleSort {
    public static void _bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
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
        _bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}