package com.home.learn.leetcode.sortnsearch;

import com.home.learn.library.TreeNode;

public class BinarySearchTreeMedian {
    public double findMedian(TreeNode root) {
        int count = count(root);
        int r0 = (count - 1) / 2 + 1;
        int r1 = count / 2 + 1;
        int[] res = new int[3];
        find(root, res, r0, r1);
        return (res[0] + res[1]) / 2.0;
    }

    private int count(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return count(root.left) + count(root.right) + 1;
    }

    private void find(TreeNode root, int[] res, int r0, int r1) {
        if(root == null || res[2] > r1) {
            return;
        }
        find(root.left, res, r0, r1);
        int idx = ++res[2];
        if(idx == r0) {
            res[0] = root.val;
        }
        if(idx == r1) {
            res[1] = root.val;
            return;
        }
        find(root.right, res, r0, r1);
    }

}
