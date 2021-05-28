package com.home.learn.facebook;

import com.home.learn.library.TreeNode;

public class SumRootToLeafNumber {
    public int sumNumbers(TreeNode root) {
        if(root == null) return 0;
        return dfsSum(root, 0, 0);
    }
    private int dfsSum(TreeNode root, int tempSum, int result) {
        int totalSum = result;
        if(root.left == null && root.right == null) {
            return tempSum * 10 + root.val + totalSum;
        }
        if(root.left != null) {
            totalSum = dfsSum(root.left, tempSum * 10 + root.val, totalSum);
        }
        if(root.right != null) {
            totalSum = dfsSum(root.right, tempSum * 10 + root.val, totalSum);
        }
        return totalSum;
    }
}
