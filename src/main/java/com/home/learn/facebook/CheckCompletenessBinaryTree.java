package com.home.learn.facebook;

import com.home.learn.library.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class CheckCompletenessBinaryTree {
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean stop = false;
        while(!q.isEmpty()) {
            TreeNode n = q.poll();
            if(n == null) {
                stop = true;
            } else {
                if(stop) return false;
                q.offer(n.left);
                q.offer(n.right);
            }
        }
        return true;
    }
}
