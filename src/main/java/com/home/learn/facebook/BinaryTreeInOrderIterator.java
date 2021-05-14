package com.home.learn.facebook;

import com.home.learn.library.TreeNode;

import java.util.Stack;

public class BinaryTreeInOrderIterator {

    private Stack<TreeNode> stack;
    public BinaryTreeInOrderIterator(TreeNode root) {
        stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        TreeNode runner = node.right;
        while (runner != null) {
            stack.push(runner);
            runner = runner.left;
        }
        return node.val;
    }
}
