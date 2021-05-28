package com.home.learn.pinterest;

import com.home.learn.library.TreeNode;

import java.util.*;

public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return false;
        }
        if(root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    //单侧有解即可，backtracking
    public List<List<Integer>> pathSumII(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(root, targetSum, res, new ArrayList<>());
        return res;
    }

    private void backtrack(TreeNode root, int targetSum, List<List<Integer>> res, List<Integer> tmp) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (root.val == targetSum) {
                tmp.add(root.val);
                res.add(new ArrayList<>(tmp));
                tmp.remove(tmp.size()-1);
            }
            return;
        }
        tmp.add(root.val);
        backtrack(root.left, targetSum - root.val, res, tmp);
        backtrack(root.right, targetSum - root.val, res, tmp);
        tmp.remove(tmp.size()-1);
    }

    //总共有几个，每个子孩子都可以当起点
    public int pathSumIIIez(TreeNode t, int sum) {
        if (t == null) return 0;
        return dfs(t, sum) + pathSumIIIez(t.left, sum) + pathSumIIIez(t.right, sum);
    }

    private int dfs(TreeNode t, int sum) {
        if (t == null) return 0;
        return ((sum - t.val == 0) ? 1 : 0) +  dfs(t.left, sum - t.val) + dfs(t.right, sum - t.val);
    }

    public int pathSumIII(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int[] result = new int[1];
        compute(root, sum, 0, result, map);
        return result[0];
    }
    //通过map记录加和可能性。每当离开时去掉自己
    //相当于找subarray和，a到b区间的加和等于sum[b] - sum[a]
    private void compute(TreeNode root, int sum, int currSum, int[] result, Map<Integer, Integer> map) {
        if(root != null) {
            currSum += root.val;
            result[0] += map.getOrDefault(currSum - sum, 0);
            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
            compute(root.left, sum, currSum, result, map);
            compute(root.right, sum, currSum, result, map);
            map.computeIfPresent(currSum, (k, v) -> v - 1);
        }
    }

    public int pathSumIV(int[] nums) {
        int[][] tree = new int[5][9];
        for (int[] ints : tree) {
            Arrays.fill(ints, -1);
        }
        for (int n : nums){
            int v = n % 10;
            n /= 10;
            int p = n % 10;
            n /= 10;
            int d = n;
            tree[d][p] = v;
        }
        int[] res = new int[1];
        dfs(tree, 1, 1, 0, res);
        return res[0];
    }

    private void dfs(int[][] tree, int d, int p, int sum, int[] res){
        if (tree[d][p] == -1)
            return;
        sum += tree[d][p];
        if (d == tree.length - 1 || (tree[d + 1][2 * p] == -1 && tree[d + 1][2 * p - 1] == -1)) {
            res[0] += sum;
        } else {
            dfs(tree, d + 1, 2 * p - 1, sum, res);
            dfs(tree, d + 1, 2 * p, sum, res);
        }
    }
}
