package com.home.learn.leetcode;

import com.home.learn.pinterest.NextPermutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationII {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(used[i] || (i > 0 && nums[i] == nums[i-1] && !used[i - 1])) continue;
                used[i] = true;
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    public List<List<Integer>> permuteUniqueV2(int[] num) {
        NextPermutation next = new NextPermutation();
        List<List<Integer>> result = new ArrayList<>();
        while (true) {
            List<Integer> res = new ArrayList<>();
            for (int n : num)
                res.add(n);
            if (!result.isEmpty() && res.equals(result.get(0)))
                break;
            result.add(res);
            next.nextPermutation(num);
        }
        return result;
    }
}
