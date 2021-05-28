package com.home.learn.facebook;

import com.home.learn.library.ListNode;

public class InsertSortedCircularLinkedList {
    static class Node {
        public int val;
        public Node next;
        public Node() {}
        public Node(int _val) {
            val = _val;
        }
        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    public Node insert(Node head, int insertVal) {
        if(head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }
        Node curr = head;
        boolean inserted = false;
        do {
            if((curr.val > curr.next.val && (insertVal > curr.val || insertVal < curr.next.val))
                || (curr.val == insertVal)
                || (curr.val < insertVal && curr.next.val > insertVal)) {
                curr.next = new Node(insertVal, curr.next);
                inserted = true;
                break;
            }
            curr = curr.next;
        } while(curr.next != head);
        if(!inserted) {
            curr.next = new Node(insertVal, curr.next);
        }
        return head;
    }
}
