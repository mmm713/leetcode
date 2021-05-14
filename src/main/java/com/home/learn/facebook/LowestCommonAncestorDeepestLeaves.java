package com.home.learn.facebook;

import com.home.learn.library.TreeNode;

public class LowestCommonAncestorDeepestLeaves {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        if(root == null) return null;
        TreeNode[] res = new TreeNode[1];
        dfs(root, 0, res);
        return res[0];
    }

    public int dfs(TreeNode root, int height, TreeNode[] res){
        if(root == null) return height;
        int left = dfs(root.left, height + 1, res);
        TreeNode resLeft = res[0];
        int right = dfs(root.right, height + 1, res);
        if(left == right) {
            res[0] = root;
            return left;
        } else if(left > right) {
            res[0] = resLeft;
            return left;
        } else {
            return right;
        }
    }
}
