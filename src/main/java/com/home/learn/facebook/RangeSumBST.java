package com.home.learn.facebook;

import com.home.learn.library.TreeNode;

public class RangeSumBST {
    public int rangeSumBST(TreeNode root, int L, int R) {
        int[] ans = {0};
        dfs(root, L, R, ans);
        return ans[0];
    }

    public void dfs(TreeNode node, int L, int R, int[] ans) {
        if (node != null) {
            if (L <= node.val && node.val <= R)
                ans[0] += node.val;
            if (L < node.val)
                dfs(node.left, L, R, ans);
            if (node.val < R)
                dfs(node.right, L, R, ans);
        }
    }
}
