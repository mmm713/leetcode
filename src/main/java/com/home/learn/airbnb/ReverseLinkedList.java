package com.home.learn.airbnb;

import com.home.learn.library.ListNode;

public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if(head==null || head.next == null){
            return head;
        }
        ListNode prev = null;
        while(head!=null){
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head= next;
        }
        return prev;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null || n == 0) {
            return head;
        }
        ListNode rHead = null;
        ListNode runner = head;
        while(--m > 0) {
            rHead = runner;
            runner = runner.next;
            n--;
        }
        ListNode prev = rHead;
        ListNode curr;
        while(--n >= 0) {
            curr = runner;
            runner = runner.next;
            curr.next = prev;
            prev = curr;
        }
        if(rHead == null) {
            head.next = runner;
            head = prev;
        } else {
            rHead.next.next = runner;
            rHead.next = prev;
        }
        return head;
    }

    public ListNode swapNodes(ListNode head, int k) {
        int listLength = 0;
        ListNode frontNode = null;
        ListNode endNode = null;
        ListNode currentNode = head;
        // set the front node and end node in single pass
        while (currentNode != null) {
            listLength++;
            if (endNode != null)
                endNode = endNode.next;
            // check if we have reached kth node
            if (listLength == k) {
                frontNode = currentNode;
                endNode = head;
            }
            currentNode = currentNode.next;
        }
        // swap the values of front node and end node
        int temp = frontNode.val;
        frontNode.val = endNode.val;
        endNode.val = temp;
        return head;
    }
}
