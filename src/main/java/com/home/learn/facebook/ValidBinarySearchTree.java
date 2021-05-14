package com.home.learn.facebook;

import com.home.learn.library.TreeNode;

import java.util.Stack;

public class ValidBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        Integer last;
        Integer current = null;
        Stack<TreeNode> stack = new Stack<>();
        //start with left;
        TreeNode node = root;
        while(node != null || !stack.empty()){
            if(node != null){
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                last = current;
                current = node.val;
                if(last != null && last >= current){
                    return false;
                }
                node = node.right;
            }
        }
        return true;
    }

    public boolean isValidBSTV2(TreeNode root) {
        return isValidBST(root, null, null);
    }
    public boolean isValidBST(TreeNode root, Integer min, Integer max){
        if(root == null)
            return true;
        if((min != null && root.val <= min) || (max != null && root.val >= max))
            return false;
        return (isValidBST(root.left, min, root.val)) && (isValidBST(root.right, root.val, max));
    }
}
