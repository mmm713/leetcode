package com.home.learn.google;

import com.home.learn.library.TreeNode;

import java.util.*;

public class DeleteNodesAndReturnForest {
    public List<TreeNode> delNodes(TreeNode root, int[] toDelete) {
        List<TreeNode> res = new ArrayList<>();
        if (root == null) return res;
        Set<Integer> del = new HashSet<>();
        for (int val : toDelete) {
            del.add(val);
        }
        if (!del.contains(root.val)) {
            res.add(root);
        }
        delete(root, del, res);
        return res;
    }

    private TreeNode delete(TreeNode root, Set<Integer> deleteSet, List<TreeNode> res) {
        if (root == null) return null;
        root.left = delete(root.left, deleteSet, res);
        root.right = delete(root.right, deleteSet, res);
        if (deleteSet.contains(root.val)) {
            if (root.left != null) {
                res.add(root.left);
            }
            if (root.right != null) {
                res.add(root.right);
            }
            return null;
        }
        return root;
    }
}
