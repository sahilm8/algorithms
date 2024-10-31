package algorithms.lc;

public class Hard {
    // 42. Trapping Rain Water (Hard) [T = O(n), S = O(1)]
    public static int trap(int[] height) {
        // TP
        int totalWater = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            // Water is determined by the smaller height
            if (height[left] < height[right]) {
                // Update leftMax
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    // Water trapped = leftMax - height[left]
                    totalWater += leftMax - height[left];
                }
                left++;
            } else {
                // Update rightMax
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    // Water trapped = rightMax - height[right]
                    totalWater += rightMax - height[right];
                }
                right--;
            }
        }
        return totalWater;
    }   
    
    // 4. Median of Two Sorted Arrays (Hard) [T = O(Log(min(m,n))), S = O(1)]
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // TP and BS
        // Perform BS with smaller array first for optimisation
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int m = nums1.length, n = nums2.length; // Lengths
        int left = 0, right = m; // Bounds for BS
        while (left <= right) {
            // Calculate the partition point in first array (X)
            int partitionX = (left + right) / 2;
            // Calculate the partiton point in the second array (Y)
            int partitionY = (m + n + 1) / 2 - partitionX;

            // Find max & min element around partitionX
            int maxLeftX = partitionX == 0 ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = partitionX == m ? Integer.MAX_VALUE : nums1[partitionX];

            // Find max & min element around partitionY
            int maxLeftY = partitionY == 0 ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = partitionY == n ? Integer.MAX_VALUE : nums2[partitionY];

            // Check if valid partition is found
            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                // If length is even, median is average of the max of left and min of right
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY)) / 2.0;
                } else {
                    // If length is odd, median is max of left elements
                    return Math.max(maxLeftX, maxLeftY);
                }
            }

            // Move the counters when the partition is too far
            if (maxLeftX > minRightY) {
                right = partitionX - 1;
            } else {
                left = partitionX + 1;
            }
        }

        // Default case
        return 0.0;
    }

    public static void main(String[] args) {
        System.out.println(trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
        System.out.println(findMedianSortedArrays(new int[] {1,2}, new int[] {3,5}));
    }
}
