package com.home.learn.facebook;

import com.home.learn.library.TreeNode;

import java.util.*;

public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        if(root == null) {
            return res;
        }
        q.offer(root);
        q.offer(null);
        int val = 0;
        while(!q.isEmpty()) {
            TreeNode n = q.poll();
            if(n == null) {
                res.add(val);
                if(!q.isEmpty()) {
                    q.offer(null);
                }
            } else {
                val = n.val;
                if(n.left != null) {
                    q.offer(n.left);
                }
                if(n.right != null) {
                    q.offer(n.right);
                }
            }
        }
        return res;
    }
}
