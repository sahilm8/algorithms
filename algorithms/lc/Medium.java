package algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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
    
    // 1823. Find the Winner of the Circular Game (Medium) [T = O(n^2), S = O(n)]
    public static int findTheWinner(int n, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int index = 0;
        while (list.size() > 1) {
            // Modulo wrap-around arithmetic: x % n in range (0, n-1)
            index = (index + k - 1) % list.size();
            list.remove(index);
        }
        return list.get(0);
    }

    // 3167. Better Compression of String (Medium) [T = O(n), S = O(k)]
    public static String betterCompression(String compressed) {
        TreeMap<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < compressed.length(); i++) {
            if (Character.isAlphabetic(compressed.charAt(i))) {
                StringBuilder s = new StringBuilder();
                for (int j = i + 1; j < compressed.length(); j++) {
                    if (Character.isDigit(compressed.charAt(j))) {
                        s.append(compressed.charAt(j));
                    } else {
                        break;
                    }
                }
                map.put(compressed.charAt(i), map.getOrDefault(compressed.charAt(i), 0)
                        + Integer.parseInt(s.toString()));
            }
        }
        StringBuilder s = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            s.append(entry.getKey());
            s.append(entry.getValue());
        }
        return s.toString();
    }

    // 3. Longest Substring Without Repeating Characters (Medium) [T = O(n), S = O(min(m, n))]
    public static int lengthOfLongestSubstring(String s) {
        // Two pointers (left, right) & Set
        Set<Character> set = new HashSet<>();
        int maxLength = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(isRobotBounded("GGLLGGLLGG"));
        System.out.println(numDecodings("226"));
        System.out.println(fractionToDecimal(-1, -2147483648));
        System.out.println(threeSum(new int[] {-1,0,1,2,-1,-4}));
        System.out.println(findTheWinner(5, 2));
        System.out.println(betterCompression("i10g6u6"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}