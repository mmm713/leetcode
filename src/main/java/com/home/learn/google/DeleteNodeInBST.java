package com.home.learn.google;

import com.home.learn.library.TreeNode;

public class DeleteNodeInBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) {
            return null;
        }
        if(root.val == key) {
            if(root.left == null) {
                return root.right;
            } else if(root.right == null) {
                return root.left;
            } else {
                TreeNode runner = root.right;
                TreeNode prev = root;
                if(runner.left == null) {
                    root.right = runner.right;
                    root.val = runner.val;
                    return root;
                }
                while(runner.left != null) {
                    prev = runner;
                    runner = runner.left;
                }
                root.val = runner.val;
                prev.left = runner.right;
            }
        } else if(root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            root.left = deleteNode(root.left, key);
        }
        return root;
    }
}
