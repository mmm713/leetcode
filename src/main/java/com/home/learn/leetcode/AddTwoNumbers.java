package com.home.learn.leetcode;

import com.home.learn.library.ListNode;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode r1 = l1, r2 = l2, previous = null;
        int carry = 0;
        while(r1 != null) {
            if(r1 != null && r2 != null) {
                int temp = r1.val;
                r1.val = (r1.val + r2.val + carry) % 10;
                carry = (temp + r2.val + carry) / 10;
                if(r1.next == null)  {
                    r1.next = r2.next;
                    r2 = null;
                    previous = r1;
                    r1 = r1.next;
                } else {
                    previous = r1;
                    r1 = r1.next;
                    r2 = r2.next;
                }
            } else if(r2 == null) {
                int temp = r1.val;
                r1.val = (r1.val + carry) % 10;
                carry = (carry + temp) / 10;
                previous = r1;
                r1 = r1.next;
            }
        }
        if(carry > 0)  {
            ListNode add = new ListNode(carry);
            previous.next = add;
        }
        return l1;
    }
}
