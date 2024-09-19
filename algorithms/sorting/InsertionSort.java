package algorithms.sorting;

import java.util.Arrays;

/*
 * Builds the final sorted array one item at a time,
 * by repeatedly inserting a new element into the sorted
 * portion of the array.
 */
public class InsertionSort {
    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arr = { 4, 6, 5, 1, 3, 2 };
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
