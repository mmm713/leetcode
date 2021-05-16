package com.home.learn.facebook;

import com.home.learn.library.TreeNode;

import java.util.Stack;

public class ConstructBinaryTreePreorderInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root;
        if(preorder.length < 1) {
            return null;
        }
        root = new TreeNode(preorder[0]);
        TreeNode runner = root;
        int j = 0;
        Stack<TreeNode> nodeStack = new Stack<>();
        nodeStack.push(runner);
        for(int i = 0; i < preorder.length - 1; i++) {
            if(nodeStack.peek().val != inorder[j]) {
                runner.left = new TreeNode(preorder[i + 1]);
                runner = runner.left;
            } else {
                while(!nodeStack.isEmpty() && nodeStack.peek().val == inorder[j]) {
                    runner = nodeStack.pop();
                    j++;
                }
                runner.right = new TreeNode(preorder[i + 1]);
                runner = runner.right;
            }
            nodeStack.push(runner);
        }
        return root;
    }

    public TreeNode buildTreeV2(int[] preorder, int[] inorder) {
        int[] idx = {0, 0};
        return build(preorder, inorder, Integer.MIN_VALUE, idx);
    }

    private TreeNode build(int[] preorder, int[] inorder, int stop, int[] idx) {
        if (idx[0] >= preorder.length) return null;
        if (inorder[idx[1]] == stop) {
            idx[1]++;
            return null;
        }
        TreeNode node = new TreeNode(preorder[idx[0]++]);
        node.left = build(preorder, inorder, node.val, idx);
        node.right = build(preorder, inorder, stop, idx);
        return node;
    }
}
