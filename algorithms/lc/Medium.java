package algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
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
        Arrays.sort(nums);
        List<List<Integer>> triplets = new ArrayList<>();
        // Counter and two pointers
        // index (i), left (i + 1), right (nums.length - 1)
        for (int i = 0; i < nums.length; i++) {
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
                    triplets.add(list);
                    while (left < right && nums[left] == nums[left + 1])
                        left++;
                    while (left < right && nums[right] == nums[right - 1])
                        right--;
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return triplets;
    }
    
    // 1823. Find the Winner of the Circular Game (Medium) [T = O(n * k), S = O(n)]
    public static int findTheWinner(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int index = 0;
        while (list.size() > 1) {
            // Modulo arithmetic: i % n in range (0, n - 1)
            index = (index + k - 1) % list.size();
            list.remove(index);
        }
        return list.get(0);
    }

    // Josephus Problem (Josephus Permutation) [T = O(n), S = O(1)]
    public static int _findTheWinner(int n, int k) {
        int winner = 1;
        for (int i = 2; i <= n; i++) {
            // + 1 to shift from 0-based to 1-based indexing
            winner = (winner + k - 1) % i + 1;
        }
        return winner;
    }

    // 3167. Better Compression of String (Medium) [T = O(n * k * log k), S = O(n * k)]
    public static String betterCompression(String compressed) {
        TreeMap<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < compressed.length(); i++) {
            if (Character.isAlphabetic(compressed.charAt(i))) {
                StringBuilder sb = new StringBuilder();
                for (int j = i + 1; j < compressed.length(); j++) {
                    if (Character.isDigit(compressed.charAt(j))) {
                        sb.append(compressed.charAt(j));
                    }
                    if (Character.isAlphabetic(compressed.charAt(j)))
                        break;
                }
                map.put(compressed.charAt(i),
                        map.getOrDefault(compressed.charAt(i), 0) + Integer.parseInt(sb.toString()));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append(entry.getValue());
        }
        return sb.toString();
    }

    // 3. Longest Substring Without Repeating Characters (Medium) [T = O(n), S = O(n)]
    public static int lengthOfLongestSubstring(String s) {
        int maxSize = 0;
        // Set & TP
        Set<Character> set = new HashSet<>();
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            if (set.contains(s.charAt(right))) {
                while (set.contains(s.charAt(right))) {
                    set.remove(s.charAt(left));
                    left++;
                }
            }
            set.add(s.charAt(right));
            maxSize = Math.max(maxSize, right - left + 1);
        }
        return maxSize;
    }

    // 49. Group Anagrams (Medium) [T = O(n * m * log(m)), S = O(n * m)]
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            map.computeIfAbsent(sorted, k -> new ArrayList<>()).add(strs[i]);
            if (!result.contains(map.get(sorted))) {
                result.add(map.get(sorted));
            }
        }
        return result;
    }

    // 198. House Robber (Medium) [T = O(n), S = O(n)]
    public static int rob(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        if (nums.length == 2)
            return Math.max(nums[0], nums[1]);
        // DP
        int[] array = new int[nums.length];
        array[0] = nums[0];
        array[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            array[i] += Math.max(array[i - 1], nums[i] + array[i - 2]);
        }
        return array[nums.length - 1];
    }

    // 647. Palindromic Substrings (Medium) [T = O(n^2), S = O(1)]
    public static int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            // Odd Palindrome (1 center) eg: abc, aaa
            count += expandOutward(s, i, i);
            // Even Palindrome (2 center) eg: abcd, aaaa
            count += expandOutward(s, i, i + 1);
        }
        return count;
    }

    // TP: Expand using left & right
    public static int expandOutward(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right <= s.length() - 1 && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    // 2375. Construct Smallest Number From DI String (Medium) [T = O(n), S = O(n)]
    public static String smallestNumber(String pattern) {
        // Stack & SB
        // IIIDIDDD
        // i=0: push(1), see 'I', pop -> sb="1"
        // i=1: push(2), see 'I', pop -> sb="12"
        // i=2: push(3), see 'I', pop -> sb="123"
        // i=3: push(4), see 'D', keep in stack
        // i=4: push(5), see 'I', pop -> sb="12354"
        // i=5: push(6), see 'D', keep in stack
        // i=6: push(7), see 'D', keep in stack
        // i=7: push(8), see 'D', keep in stack
        // i=8: push(9), end reached, pop all -> sb="123549876"
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= pattern.length(); i++) {
            stack.push(i + 1);
            if (i == pattern.length() || pattern.charAt(i) == 'I') {
                while(!stack.isEmpty()) {
                    // Gradually building the lexicographically smallest string
                    sb.append(stack.pop());
                }
            }
        }
        return sb.toString();
    }

    // 11. Container With Most Water (Medium) [T = O(n), S = O(1)]
    public static int maxArea(int[] height) {
        // TP
        int maxArea = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int length = Math.min(height[left], height[right]);
            int breadth = right - left;
            int area = length * breadth;
            maxArea = Math.max(area, maxArea);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    // 22. Generate Parentheses (Medium) [T = O(2^n), S = O(n)]
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(result, "", 0, 0, n);
        return result;
    }

    // Recursive Backtracking
    public static void backtrack(List<String> result, String s, int open, int close, int n) {
        if (s.length() == n * 2) { // each valid compination has n * 2 chars
            result.add(s);
            return;
        }
        // less open brackets than n
        if (open < n) {
            backtrack(result, s + "(", open + 1, close, n);
        }
        // more open brackets than close brackets
        if (open > close) {
            backtrack(result, s + ")", open, close + 1, n);
        }
    }

    // 55. Jump Game (medium) [T = O(n), S = O(1)]
    public static boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i <= max && i < nums.length; i++) {
            if (max >= nums.length - 1) return true;
            max = Math.max(max, i + nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isRobotBounded("GGLLGGLLGG"));
        System.out.println(numDecodings("226"));
        System.out.println(fractionToDecimal(-1, -2147483648));
        System.out.println(threeSum(new int[] {-1,0,1,2,-1,-4}));
        System.out.println(_findTheWinner(5, 2));
        System.out.println(betterCompression("i10g6u6"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(groupAnagrams(new String[] {"eat","tea","tan","ate","nat","bat"}));
        System.out.println(rob(new int[] {2,1,1,2}));
        System.out.println(countSubstrings("aaa"));
        System.out.println(smallestNumber("IIIDIDDD"));
        System.out.println(maxArea(new int[] {1,8,6,2,5,4,8,3,7}));
        System.out.println(generateParenthesis(3));
        System.out.println(canJump(new int[] {2,3,1,1,4}));
    }
}