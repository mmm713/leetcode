package com.home.learn.microsoft;

import com.home.learn.library.TreeNode;

public class BinaryTreeMaxPathSum {
    public int maxPathSum(TreeNode root) {
        int[] res = {root.val};
        maxSum(root, res);
        return res[0];
    }
    private int maxSum(TreeNode root, int[] result) {
        if(root.left == null && root.right == null){
            return root.val;
        } else {
            int leftVal = 0, rightVal = 0, temp = 0;
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
}
