package com.home.learn.pinterest;

import com.home.learn.library.TreeNode;

import java.util.*;

public class BinaryTreeZigZagOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<List<Integer>> results = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        q.offer(null);

        LinkedList<Integer> temp = new LinkedList<>();
        boolean orderLeft = true;

        while (q.size() > 0) {
            TreeNode curr = q.poll();
            if (curr != null) {
                if (orderLeft)
                    temp.addLast(curr.val);
                else
                    temp.addFirst(curr.val);
                if (curr.left != null)
                    q.offer(curr.left);
                if (curr.right != null)
                    q.offer(curr.right);
            } else {
                // we finish the scan of one level
                results.add(temp);
                temp = new LinkedList<>();
                // prepare for the next level
                if (q.size() > 0)
                    q.offer(null);
                orderLeft = !orderLeft;
            }
        }
        return results;
    }
}
