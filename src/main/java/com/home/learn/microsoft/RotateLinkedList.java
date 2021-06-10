package com.home.learn.microsoft;

import com.home.learn.library.ListNode;

public class RotateLinkedList {
    public static void main(String[] args) {

    }
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return null;
        else if(head.next == null) return head;
        if(k == 0) return head;
        int len = 1;
        ListNode runner = head;
        while(runner.next != null){
            len++;
            runner = runner.next;
        }
        k = len - k % len;
        if(k == 0) return head;
        runner.next = head;
        while(k > 0){
            runner = runner.next;
            k--;
        }
        head = runner.next;
        runner.next = null;
        return head;
    }
}
