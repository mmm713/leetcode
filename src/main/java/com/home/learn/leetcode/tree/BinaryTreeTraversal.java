package com.home.learn.leetcode.tree;

import com.home.learn.library.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        TreeNode runner;
        while(cur != null) {
            if(cur.left == null) {
                res.add(cur.val);
                cur = cur.right;
            } else {
                runner = cur.left;
                while(runner.right != null && runner.right != cur) {
                    runner = runner.right;
                }
                if(runner.right == null) {
                    runner.right = cur;
                    cur = cur.left;
                } else {
                    res.add(cur.val);
                    runner.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        TreeNode current;
        current = root;
        while(current != null) {
            if(current.left == null) {
                result.add(current.val);
                current = current.right;
            } else {
                TreeNode runner = current.left;
                while(runner.right != null && runner.right != current)  {
                    runner = runner.right;
                }
                if(runner.right == null)  {
                    result.add(current.val);
                    runner.right = current;
                    current = current.left;
                } else {
                    runner.right = null;
                    current = current.right;
                }
            }
        }
        return result;
    }


    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        TreeNode current;
        current = root;
        while(current != null) {
            if(current.right == null) {
                result.add(0, current.val);
                current = current.left;
            }
            else {
                TreeNode runner = current.right;
                while(runner.left != null && runner.left != current) {
                    runner = runner.left;
                }
                if(runner.left == null) {
                    result.add(0, current.val);
                    runner.left = current;
                    current = current.right;
                } else {
                    runner.left = null;
                    current = current.left;
                }
            }
        }
        return result;
    }
}
