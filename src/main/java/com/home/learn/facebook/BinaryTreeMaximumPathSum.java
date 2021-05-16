package com.home.learn.facebook;

import com.home.learn.library.TreeNode;

public class BinaryTreeMaximumPathSum {
    public int maxPathSum(TreeNode root) {
        int[] res = {root.val};
        maxSum(root, res);
        return res[0];
    }
    private int maxSum(TreeNode root, int[] result) {
        int leftVal = 0, rightVal = 0;
        if(root.left != null){
            leftVal = maxSum(root.left, result);
            result[0] = Math.max(leftVal, result[0]);
        }
        if(root.right != null){
            rightVal = maxSum(root.right, result);
            result[0] = Math.max(rightVal, result[0]);
        }
        result[0] = Math.max((rightVal + root.val), result[0]);
        result[0] = Math.max((leftVal + root.val), result[0]);
        result[0] = Math.max((leftVal + rightVal + root.val), result[0]);
        return Math.max(Math.max(leftVal + root.val, root.val), rightVal + root.val);
    }
}
