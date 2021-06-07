package com.home.learn.facebook;

import com.home.learn.library.ListNode;

public class ReorderList {
    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        if(head == null || head.next == null || head.next.next == null) {
            return;
        }
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //目前ptr0为最终终止位置
        ListNode temp = slow;
        slow = slow.next;
        temp.next = null;
        temp = null;
        fast = slow.next;
        //把后半截链表反向
        while(fast != null) {
            slow.next = temp;
            temp = slow;
            slow = fast;
            fast = fast.next;
        }
        slow.next = temp;
        //最终结果与前半截merge
        while(head != null && slow != null) {
            temp = head.next;
            head.next = slow;
            head = temp;
            temp = slow.next;
            slow.next = head;
            slow = temp;
        }
    }
}
