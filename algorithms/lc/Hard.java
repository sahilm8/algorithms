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
    
    public static void main(String[] args) {
        System.out.println(trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
    }
}
