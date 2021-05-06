package com.home.learn.pinterest;

import com.home.learn.library.ListNode;

public class MergeKLists {
    //T(k) = 2T(k/2)+O(n*k)
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length < 1) return null;
        int end = lists.length - 1;
        while(end > 0) {
            int begin = 0;
            while(begin<end) {
                lists[begin] = mergeTwoLists(lists[begin], lists[end]);
                begin++;
                end--;
            }
        }
        return lists[0];
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(9999);
        ListNode now = result;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                now.next = l2;
                l2 = l2.next;
            } else {
                now.next  = l1;
                l1 = l1.next;
            }
            now = now.next;
        }
        now.next = l1 != null ? l1 : l2;
        result = result.next;
        return result;
    }
}
