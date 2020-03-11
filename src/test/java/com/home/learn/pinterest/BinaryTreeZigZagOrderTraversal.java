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
        boolean is_order_left = true;

        while (q.size() > 0) {
            TreeNode curr_node = q.poll();
            if (curr_node != null) {
                if (is_order_left)
                    temp.addLast(curr_node.val);
                else
                    temp.addFirst(curr_node.val);
                if (curr_node.left != null)
                    q.offer(curr_node.left);
                if (curr_node.right != null)
                    q.offer(curr_node.right);
            } else {
                // we finish the scan of one level
                results.add(temp);
                temp = new LinkedList<>();
                // prepare for the next level
                if (q.size() > 0)
                    q.offer(null);
                is_order_left = !is_order_left;
            }
        }
        return results;
    }
}
