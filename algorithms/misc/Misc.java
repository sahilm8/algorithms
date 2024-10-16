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
        ListNode current = new ListNode();
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
        }
        if (list1 != null) {
            current.next = list1;
        }
        if (list2 != null) {
            current.next = list2;
        }
        return current.next;
    } 

    public static void main(String[] args) {
        System.out.println(isValid("([]"));
    }
}