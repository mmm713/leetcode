package com.home.learn.leetcode.tree;

import com.home.learn.library.TreeNode;

public class TreeToDoubleList {
    public TreeNode treeToDoublyListMorris(TreeNode root) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        TreeNode cur = root, head = null, tail = null, tmp;
        // morris' algo
        while (cur != null) {
            head = cur.val < min ? cur : head;
            min = Math.min(min, cur.val);
            tail = cur.val > max ? cur : tail;
            max = Math.max(max, cur.val);
            // right most on left subtree
            TreeNode rlmost = cur.left;
            while (rlmost != null
                    && rlmost.right != null && rlmost != cur) {
                rlmost = rlmost.right;
            }
            tmp = cur.left;
            if (rlmost != null && rlmost != cur) {
                // link rlmost node and cur
                // if still can go left then go
                rlmost.right = cur;
                cur.left = rlmost;
                cur = tmp;
                continue;
            }
            // no left substree or linked before, go right
            // find the left most node of right subtree
            TreeNode lrmost = cur.right;
            while (lrmost != null
                    && lrmost.left != null && lrmost.left != cur) {
                // note that if lrmost.left (lrmost.pre) is cur
                // which means cur and lrmost are already in linked list
                // then just skip
                lrmost = lrmost.left;
            }
            tmp = cur.right;
            if (lrmost != null && lrmost.left != cur) {
                lrmost.left = cur;
                cur.right = lrmost;
            }
            cur = tmp;
        }
        // cyclic linking
        if (head != null) {
            tail.right = head;
            head.left = tail;
        }
        return head;
    }

    public TreeNode treeToDoublyList(TreeNode root) {
        if(root == null)  return null;
        TreeNode[] headTail = new TreeNode[2];
        inOrder(root, headTail);
        headTail[1].right = headTail[0];
        headTail[0].left = headTail[1];
        return headTail[0];
    }

    private void inOrder(TreeNode root, TreeNode[] headTail) {
        if(root == null) return;
        inOrder(root.left, headTail);
        if(headTail[0] == null) {
            headTail[0] = root;
        } else {
            headTail[1].right = root;
            root.left = headTail[1];
        }
        headTail[1] = root;
        inOrder(root.right, headTail);
    }
}
