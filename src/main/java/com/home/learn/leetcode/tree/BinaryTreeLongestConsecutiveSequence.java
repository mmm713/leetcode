package com.home.learn.leetcode.tree;

import com.home.learn.library.TreeNode;

public class BinaryTreeLongestConsecutiveSequence {
    //只能从父到子连续递增
    public int longestConsecutive(TreeNode root) {
        int[] res = new int[1];
        dfs(root, res);
        return res[0];
    }

    private int[] dfs(TreeNode root, int[] res) {
        if(root == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs(root.left, res);
        int[] r = dfs(root.right, res);
        int ans = 0;
        if(l[0] != 0 && l[1] - root.val == 1) {
            ans = l[0];
        }
        if(r[0] != 0 && r[1] - root.val == 1) {
            ans = Math.max(ans, r[0]);
        }
        res[0] = Math.max(res[0], ans + 1);
        return new int[]{ans + 1, root.val};
    }

    //升降序都可以且可以跨父节点
    public int longestConsecutiveII(TreeNode root) {
        int[] res = new int[1];
        dfsII(root, res);
        return res[0];
    }

    //返回 最长递增，最长递减，当前值
    private int[] dfsII(TreeNode root, int[] res) {
        if(root == null) {
            return new int[]{0, 0, 0};
        }
        int[] l = dfsII(root.left, res);
        int[] r = dfsII(root.right, res);
        int ia = 0;
        int da = 0;
        int[] ans = new int[2];
        if(l[2] - root.val == 1 && l[0] != 0) {
            ia = l[0];
            ans[0] = l[0];
        }
        if(l[2] - root.val == -1 && l[1] != 0) {
            da = l[1];
            ans[1] = l[1];
        }
        if(r[2] - root.val == 1 && r[0] != 0) {
            ia = Math.max(ia, r[0]);
            ans[1] = ans[1] + r[0];
        }
        if(r[2] - root.val == -1 && r[1] != 0) {
            da = Math.max(da, r[1]);
            ans[0] = ans[0] + r[1];
        }
        res[0] = Math.max(res[0], Math.max(ans[0], ans[1]) + 1);
        return new int[]{ia + 1, da + 1, root.val};
    }
}
