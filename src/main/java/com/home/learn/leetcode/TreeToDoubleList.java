package com.home.learn.leetcode;

import com.home.learn.library.TreeNode;

public class TreeToDoubleList {
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
            headTail[1] = root;
        } else {
            headTail[1].right = root;
            root.left = headTail[1];
            headTail[1] = root;
        }
        inOrder(root.right, headTail);
    }
}
