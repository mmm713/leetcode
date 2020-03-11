package com.home.learn.pinterest;

import com.home.learn.library.ListNode;
import com.home.learn.library.TreeNode;

public class SortedListToBinarySearchTree {
    public TreeNode sortedListToBST(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.right = sortedListToBST(slow.next);
        if(prev != null) {
            prev.next = null;
            root.left = sortedListToBST(head);
        }
        return root;
    }
}
