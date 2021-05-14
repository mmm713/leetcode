package com.home.learn.facebook;

import com.home.learn.library.ListNode;

public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode curr = head;
        ListNode prev = null;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        if (fast != null)
            curr = curr.next;
        while (curr != null && prev != null) {
            if (curr.val != prev.val) {
                return false;
            } else {
                curr = curr.next;
                prev = prev.next;
            }
        }
        return true;
    }
}
