package com.home.learn.bytedance;

import com.home.learn.library.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class EqualTreePartition {
    public boolean checkEqualTree(TreeNode root) {
        Set<Integer> sum = new HashSet<>();
        int total = sum(root, sum, root);
        return total % 2 == 0 && sum.contains(total / 2);
    }

    public int sum(TreeNode node, Set<Integer> sum, TreeNode root) {
        if (node == null) return 0;
        int total = sum(node.left, sum, root) + sum(node.right, sum, root) + node.val;
        if(node != root) {
            sum.add(total);
        }
        return total;
    }
}
