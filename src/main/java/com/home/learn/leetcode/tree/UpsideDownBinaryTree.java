package com.home.learn.leetcode.tree;


import com.home.learn.library.TreeNode;

public class UpsideDownBinaryTree {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root == null || root.left == null) return root;
        TreeNode left = root.left;
        TreeNode right = root.right;
        TreeNode res = upsideDownBinaryTree(left);
        left.left = right;
        left.right =root;
        root.left = null;
        root.right = null;
        return res;
    }
}
