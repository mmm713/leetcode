package com.home.learn.smartnews;

import com.home.learn.library.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class FindLargestValueInEachTreeRow {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if(root == null) {
            return res;
        }
        int counter = 1;
        queue.add(root);
        int max = Integer.MIN_VALUE;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            max = Math.max(max, node.val);
            if(node.left != null) {
                queue.add(node.left);
            }
            if(node.right != null) {
                queue.add(node.right);
            }
            counter--;
            if(counter == 0) {
                counter = queue.size();
                res.add(max);
                max = Integer.MIN_VALUE;
            }
        }
        return res;
    }
}
