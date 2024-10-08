package algorithms.design;

/*
 * This implementation demonstrates the sliding window technique to find
 * the maximum sum of a subarray (consecutive elements) of size k in a given
 *  array. It efficiently maintains a window of size k and slides it through
 * the array, updating the maximum sum found so far. This approach achieves
 * O(n) time complexity, making it more efficient than the naive approach of
 * checking all subarrays.
 */
public class SlidingWindow {
    public static int slidingWindow(int[] arr, int k) {
        int windowSum = 0, maxSum = 0;
        // Calculate first window's sum
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        maxSum = windowSum;
        // Slide the window and calculate its sum
        for (int i = k; i < arr.length; i++) {
            // Slide by removing the first element and adding a new one
            windowSum = windowSum - arr[i - k] + arr[i];
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 10, 23, 3, 1, 0, 20};
        int k = 4;
        System.out.println("Maximum sum of subarray of size " + k + ": " + slidingWindow(arr, k));
    }
}