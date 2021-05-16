package com.home.learn.leetcode;

import com.home.learn.library.ListNode;

public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode node = head;
        ListNode previous = head;
        while (node != null && node.next != null) {
            if (previous == head) {
                head = head.next;
                node.next = head.next;
                head.next = node;
            } else {
                previous.next = node.next;
                node.next = node.next.next;
                previous.next.next = node;
            }
            previous = node;
            node = node.next;
        }

        return head;
    }
}
