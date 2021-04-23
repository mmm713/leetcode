package com.home.learn.microsoft;

import com.home.learn.library.TreeNode;

public class CountGoodNodesInBinaryTree {
    public int goodNodes(TreeNode root) {
        return dfsNodes(root, root.val);
    }

    private int dfsNodes(TreeNode root, int maxValue) {
        if(root == null) {
            return 0;
        }
        int count = 0;
        int max = maxValue;
        if(root.val >= maxValue) {
            count = 1;
            max = root.val;
        }
        return count + dfsNodes(root.left, max) + dfsNodes(root.right, max);
    }
}
