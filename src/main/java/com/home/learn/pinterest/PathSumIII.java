package com.home.learn.pinterest;

import com.home.learn.library.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class PathSumIII {
    public int pathSum(TreeNode t, int sum) {
        if (t == null) return 0;
        return dfs(t, sum) + pathSum(t.left, sum) + pathSum(t.right, sum);
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

    private void compute(TreeNode root, int sum, int currSum, int[] result, Map<Integer, Integer> map) {
        if(root != null) {
            currSum += root.val;
            result[0] += map.getOrDefault(currSum - sum, 0);
            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
            compute(root.left, sum, currSum, result, map);
            compute(root.right, sum, currSum, result, map);
            map.put(currSum, map.get(currSum) - 1);
        }
    }
}
