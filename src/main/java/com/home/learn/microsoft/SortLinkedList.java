package com.home.learn.microsoft;

import com.home.learn.library.ListNode;

public class SortLinkedList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head, fast = head.next.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode p2 = sortList(slow.next);
        slow.next = null;
        ListNode p1 = sortList(head);
        return merge(p1, p2);
    }
    private ListNode merge(ListNode p1, ListNode p2) {
        ListNode p, head = new ListNode(0);
        p = head;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val)
            {
                p.next = p1;
                p1 = p1.next;
            }
            else
            {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        p.next = (p1 ==null) ? p2 : p1;
        return head.next;
    }


    public ListNode insertionSortList(ListNode head) {
        ListNode p0 = head;
        ListNode result = head;
        if(result == null) {
            return null;
        }
        while(p0.next != null) {
            if(p0.next.val < p0.val) {
                ListNode temp = result;
                if(p0.next.val < temp.val) {
                    temp = p0.next;
                    p0.next = p0.next.next;
                    temp.next = result;
                    result = temp;
                } else {
                    while(temp.next.val < p0.next.val)
                    {
                        temp = temp.next;
                    }
                    ListNode p1 = p0.next;
                    p0.next = p0.next.next;
                    p1.next = temp.next;
                    temp.next = p1;
                }
            } else {
                p0 = p0.next;
            }
        }
        return result;
    }
}
