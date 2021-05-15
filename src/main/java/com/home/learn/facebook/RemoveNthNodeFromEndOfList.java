package com.home.learn.facebook;

import com.home.learn.library.ListNode;

public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode tail = head;
        while(n > 0 && tail.next != null) {
            n--;
            tail = tail.next;
        }
        if(n > 0) {
            return head.next;
        }
        ListNode curr = head;
        while(tail.next != null) {
            curr = curr.next;
            tail = tail.next;
        }
        curr.next = curr.next.next;
        return head;
    }
}
