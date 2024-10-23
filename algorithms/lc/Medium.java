package algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Medium {
     // 1041. Robot Bounded in Circle (Medium) [T = O(n), S = O(1)]
    public static boolean isRobotBounded(String instructions) {
        int x = 0, y = 0;
        int facing = 0; // N E S W
        int[][] directions = new int[][] { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } }; // N E S W
        for (int i = 0; i < instructions.length(); i++) {
            if (instructions.charAt(i) == 'L') {
                facing = (facing + 3) % 4; // Modulo wrap-around arithmetic: x % n in range (0, n-1)
            } else if (instructions.charAt(i) == 'R') {
                facing = (facing + 1) % 4;
            } else {
                x += directions[facing][0];
                y += directions[facing][1];
            }
        }
        if ((x == 0 && y == 0) || facing != 0)
            return true; // Bounded circle
        return false;
    }

    // 91. Decode Ways (Medium) [T = O(n), S = O(n)]
    public static int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0')
            return 0;
        // Dynamic programming: dynamically build up to the result
        int[] array = new int[s.length() + 1]; // 0 to s.length inclusive
        // Example: 226 => 1 1 2 3
        array[0] = 1; // DP convention
        array[1] = 1; // 1 way to decode a single number
        for (int i = 2; i <= s.length(); i++) {
            int singleDigit = Integer.parseInt(s.substring(i - 1, i));
            int doubleDigit = Integer.parseInt(s.substring(i - 2, i));
            if (singleDigit >= 1 && singleDigit <= 9) {
                array[i] += array[i - 1];
            }
            if (doubleDigit >= 10 && doubleDigit <= 26) {
                array[i] += array[i - 2];
            }
        }
        return array[array.length - 1];
    }

    // 166. Fraction to Recurring Decimal (Medium) [T = O(log n+d), S = O(n+d)]
    public static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder result = new StringBuilder();
        // XOR: when both have diff signs
        if ((numerator < 0) ^ (denominator < 0))
            result.append("-");
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);
        result.append(num / den);
        // Manual long division to find repeating remainder
        HashMap<Long, Integer> map = new HashMap<>();
        long remainder = num % den;
        if (remainder != 0)
            result.append(".");
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                // For repeating remainder,
                // get the index and insert "(" and append ")"
                result.insert(map.get(remainder), "(");
                result.append(")");
                break;
            }
            // Put last index + 1 (result.length()) into the map
            map.put(remainder, result.length());
            remainder *= 10;
            result.append(remainder / den);
            remainder %= den;
        }
        return result.toString();
    }

    // 15. 3Sum (Medium) [T = O(n^2), S = O(1)]
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // ALWAYS sort for two pointers
        Arrays.sort(nums);
        // A counter and two pointers (i, left, right)
        for (int i = 0; i < nums.length; i++) {
            // Skip duplicate element
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println(isRobotBounded("GGLLGGLLGG"));
        System.out.println(numDecodings("226"));
        System.out.println(fractionToDecimal(-1, -2147483648));
        System.out.println(threeSum(new int[] {-1,0,1,2,-1,-4}));
    }
}