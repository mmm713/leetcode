package com.home.learn.leetcode;

import com.home.learn.library.ListNode;

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode r1 = l1, r2 = l2, previous = null;
        int carry = 0;
        while(r1 != null) {
            int temp = r1.val;
            if(r2 != null) {
                r1.val = (r1.val + r2.val + carry) % 10;
                carry = (temp + r2.val + carry) / 10;
                if(r1.next == null)  {
                    r1.next = r2.next;
                    r2 = null;
                } else {
                    r2 = r2.next;
                }
            } else {
                r1.val = (r1.val + carry) % 10;
                carry = (carry + temp) / 10;
            }
            previous = r1;
            r1 = r1.next;
        }
        if(carry > 0)  {
            previous.next = new ListNode(carry);
        }
        return l1;
    }

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }
}
