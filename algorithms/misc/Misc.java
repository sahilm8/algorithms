package algorithms.misc;

import java.util.HashMap;
import java.util.Stack;

// LeetCode Answers
public class Misc {
    // 6. Two Sum
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int index = 0;
        while (index < nums.length) {
            if (map.containsKey(target - nums[index])) {
                return new int[] {index, map.get(target - nums[index])};
            }
            map.put(nums[index], index);
            index++;
        }
        return new int[] {};
    }

    // 20. Valid Parenthesis
    public static boolean isValid(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 1) return false;
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        int i = 0;
        while (i < chars.length) {
            if (map.containsValue(chars[i])) {
                stack.push(chars[i]);
            } else if (!stack.isEmpty() && stack.peek() == map.get(chars[i])) {
                stack.pop();
            } else {
                return false;
            }
            i++;
        }
        return stack.isEmpty();
    }

    // 21. Merge Two Sorted Lists
    // Singly-linked list
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
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
        return dummyHead.next; // returning the linked list after the dummyHead
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

    // 104. Max Depth of a Binary Tree
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Calculated using resursive DFS (Depth First Search)
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return 1 + Math.max(leftDepth, rightDepth);
    }

    // 206. Reverse Linked List
    // iterative
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        while (current != null) {
            ListNode temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;
    }

    // recursive
    public static ListNode _reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode reversedRest = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return reversedRest;
    }

    // 1041. Robot Bounded in Circle
    public static boolean isRobotBounded(String instructions) {
        int x = 0, y = 0;
        int facing = 0; // N E S W
        int[][] directions = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // N E S W
        for (int i = 0; i < instructions.length(); i++) {
            if (instructions.charAt(i) == 'L') {
                // Modular arithmetic:
                // any number % n to will be in the range 0 to n-1 inclusive
                // Hence, 0 to 3, i.e. 0 = N, 1 = E, 2 = S, 3 = W
                facing = (facing + 3) % 4; // +3 to turn left
            } else if (instructions.charAt(i) == 'R') {
                facing = (facing + 1) % 4; // +1 to turn right
            } else {
                x += directions[facing][0];
                y += directions[facing][1];
            }
        }
        if ((x == 0 && y == 0) || facing != 0) return true;
        return false;
    }

    // 91. Decode Ways
    public static int numDecodings(String s) {
        int ways = 1;
        if (s.toCharArray().length == 1) {
            if (s.charAt(0) == 0) {
                return 0;
            } else {
                return ways;
            }
        }
        int current = 0;
        while (current < s.toCharArray().length - 1) {
            int next = current + 1;
            if (Integer.parseInt(new String(new char[] {s.charAt(current), s.charAt(next)})) <= 26) {
                ways++;
            }
            current++;
        }
        return ways;
    }

    public static void main(String[] args) {
        System.out.println(isValid("([]"));
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        System.out.println(listNodeToString(mergeTwoLists(list1, list2)));
        System.out.println(maxDepth(new TreeNode(3, new TreeNode(9, null, null), new TreeNode(20, new TreeNode(15), new TreeNode(7)))));
        System.out.println(listNodeToString(_reverseList(new ListNode(1, new ListNode(2, new ListNode(3))))));
        System.out.println(isRobotBounded("GLRLLGLL"));
    }
}