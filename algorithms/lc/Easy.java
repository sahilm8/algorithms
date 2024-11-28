package algorithms.lc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Easy {
    // 6. Two Sum (Easy) [T = O(n), S = O(n)]
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] { i, map.get(target - nums[i]) };
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }

    // 20. Valid Parenthesis (Easy) [T = O(n), S = O(n)]
    public static boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        if (s.length() < 2 || map.containsKey(s.charAt(0)))
            return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsValue(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else {
                if (!stack.isEmpty() && stack.peek() == map.get(s.charAt(i))) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        if (stack.isEmpty())
            return true;
        return false;
    }

    // 21. Merge Two Sorted Lists (Easy) [T = O(n + m), S = O(1)]
    // Singly-linked list
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode current = head;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        if (list1 != null) {
            current.next = list1;
        }
        if (list2 != null) {
            current.next = list2;
        }
        return head.next; // Returning the node after head
    }

    public static String listNodeToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append(", ");
            }
            head = head.next;
        }
        sb.append("]");
        return sb.toString();
    }

    // 104. Max Depth of a Binary Tree (Easy) [T = O(n), S = O(h)]
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        // Recursive DFS (Depth-First Search)
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return 1 + Math.max(left, right);
    }

    // 206. Reverse Linked List (Easy) [T = O(n), S = O(1)]
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = prev;
            prev = head;
            head = temp;
        }
        return prev;
    }

    // 387. First Unique Character in a String (Easy) [T = O(n), S = O(1)]
    public static int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    // 121. Best Time to Buy and Sell Stock (Easy) [T = O(n), S = O(1)]
    public static int maxProfit(int[] prices) {
        int lowestPrice = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < lowestPrice)
                lowestPrice = prices[i];
            if (prices[i] - lowestPrice > maxProfit)
                maxProfit = prices[i] - lowestPrice;
        }
        return maxProfit;
    }

    // 1086. High Five (Easy) [T = O(n log(n)), S = O(n)]
    public static int[][] highFive(int[][] items) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < items.length; i++) {
            map.putIfAbsent(items[i][0], new ArrayList<>());
            map.get(items[i][0]).add(items[i][1]);
        }
        int[][] result = new int[map.size()][2];
        int i = 0;
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int avg = (int) entry
                    .getValue()
                    .stream()
                    .sorted(Collections.reverseOrder())
                    .limit(5)
                    .mapToInt(Integer::intValue)
                    .average()
                    .orElse(0);
            result[i][0] = entry.getKey();
            result[i][1] = avg;
            i++;
        }
        return result;
    }

    // 844. Backspace String Compare (Easy) [T = O(n), S = O(n)]
    public static boolean backspaceCompare(String s, String t) {
        return formatString(s).equals(formatString(t)) ? true : false;
    }

    public static String formatString(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#' && !stack.isEmpty()) {
                stack.pop();
            }
            if (s.charAt(i) != '#') {
                stack.add(s.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            sb.append(stack.get(i));
        }
        return sb.toString();
    }

    // 70. Climb Stairs (Easy) [T = O(n), S = O(n)]
    public static int climbStairs(int n) {
        // DP
        // 1 2 3
        // 1 -> 1-2-3
        // 2 -> 1 2-3
        // 3 -> 1-2 3
        int[] array = new int[n + 1];
        array[0] = 1;
        array[1] = 1;
        for (int i = 2; i <= n; i++) {
            array[i] += array[i - 2] + array[i - 1];
        }
        return array[n];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[] { 1, 2, 3, 4 }, 6)));
        System.out.println(isValid("([]"));
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        System.out.println(listNodeToString(mergeTwoLists(list1, list2)));
        System.out.println(maxDepth(
                new TreeNode(3, new TreeNode(9, null, null), new TreeNode(20, new TreeNode(15), new TreeNode(7)))));
        System.out.println(firstUniqChar("loveleetcode"));
        System.out.println(maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));
        System.out.println(Arrays.deepToString(highFive(
                new int[][] { { 1, 91 }, { 1, 92 }, { 2, 93 }, { 2, 97 }, { 1, 60 }, { 2, 77 }, { 1, 65 }, { 1, 87 },
                        { 1, 100 }, { 2, 100 }, { 2, 76 } })));
        System.out.println(backspaceCompare("ab#c", "ad#c"));
        System.out.println(climbStairs(3));
    }
}