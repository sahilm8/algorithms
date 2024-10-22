package algorithms.lc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Easy {
    // 6. Two Sum (Easy) [T = O(n), S = O(n)]
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] { map.get(target - nums[i]), i };
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }

    // 20. Valid Parenthesis (Easy) [T = O(n), S = O(n/2) (worst case = O(n))]
    public static boolean isValid(String s) {
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        if (s.length() < 2 || map.containsKey(s.charAt(0)))
            return false;
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
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
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

    // 104. Max Depth of a Binary Tree (Easy) [T = O(n), S = O(h) (worst case = O(n), best case = O(log n))]
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

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        // Recursive DFS (Depth-First Search)
        int left = maxDepth(root.left);
        int right =  maxDepth(root.right);
        return 1 + Math.max(left, right);
    }

    // 206. Reverse Linked List (Easy) [T = O(n), S = O(1)]
    public ListNode reverseList(ListNode head) {
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

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[] {1, 2, 3, 4}, 6)));
        System.out.println(isValid("([]"));
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        System.out.println(listNodeToString(mergeTwoLists(list1, list2)));
        System.out.println(maxDepth(new TreeNode(3, new TreeNode(9, null, null), new TreeNode(20, new TreeNode(15), new TreeNode(7)))));
    }
}