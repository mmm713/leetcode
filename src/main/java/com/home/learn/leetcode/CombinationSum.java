package com.home.learn.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(result, new ArrayList<>(), candidates, 0, target);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> temp, int[] candidates, int start, int target) {
        if(target == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for(int i = start; i < candidates.length; i++) {
            if(candidates[i] > target) return;
            temp.add(candidates[i]);
            backtrack(result, temp, candidates, i, target - candidates[i]);
            temp.remove(temp.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack2(result, new ArrayList<>(), candidates, 0, target);
        return result;
    }

    private void backtrack2(List<List<Integer>> result, List<Integer> temp, int[] candidates, int start, int target) {
        if(target == 0) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for(int i = start; i < candidates.length; i++) {
            if(candidates[i] > target) return;
            if(i > start && candidates[i] == candidates[i - 1]) continue;
            temp.add(candidates[i]);
            backtrack2(result, temp, candidates, i + 1, target - candidates[i]);
            temp.remove(temp.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack3(result, new ArrayList<>(), n, 1, k);
        return result;
    }

    private void backtrack3(List<List<Integer>> result, List<Integer> temp, int n, int start, int k) {
        if(n == 0 && k == 0) {
            result.add(new ArrayList<>(temp));
            return;
        } else if(k == 0 || n == 0) {
            return;
        }
        for(int i = start; i <= 9; i++) {
            //this if statement is to boost
            if(n - i - (k - 1) * 9 > 0) continue;
            temp.add(i);
            backtrack3(result, temp, n - i, i + 1, k - 1);
            temp.remove(temp.size() - 1);
        }
    }

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (num <= i) dp[i] += dp[i - num];
            }
        }
        return dp[target];
    }
}
