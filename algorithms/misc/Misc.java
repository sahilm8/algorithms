package algorithms.misc;

import java.util.HashMap;
import java.util.Stack;

// LeetCode Answers
public class Misc {
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

    public static void main(String[] args) {
        System.out.println(isValid("([]"));
        ListNode list1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        System.out.println(listNodeToString(mergeTwoLists(list1, list2)));
        System.out.println(maxDepth(new TreeNode(3, new TreeNode(9, null, null), new TreeNode(20, new TreeNode(15), new TreeNode(7)))));
        System.out.println(listNodeToString(_reverseList(new ListNode(1, new ListNode(2, new ListNode(3))))));
    }
}