package com.home.learn.leetcode;

import com.home.learn.Helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permute(nums, 0, nums.length, result);
        return result;
    }

    private void permute(int[] nums, int start, int end, List<List<Integer>> output) {
        if (start == end) {
            output.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        for (int i = start; i < end; i++) {
            swap(nums, start, i);
            permute(nums, start + 1, end, output);
            swap(nums, start, i);
        }
    }

    private void swap(int[] arr, int idx_a, int idx_b) {
        int tmp = arr[idx_a];
        arr[idx_a] = arr[idx_b];
        arr[idx_b] = tmp;
    }


    public List<List<Integer>> permuteV2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfsPermu(nums, new ArrayList<>(), result);
        return result;
    }
    private void dfsPermu(int[] nums, List<Integer> temp, List<List<Integer>> result) {
        if(temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
        }
        for (int num : nums) {
            if (temp.contains(num)) continue;
            temp.add(num);
            dfsPermu(nums, temp, result);
            temp.remove(temp.size() - 1);
        }
    }
/*
=====================
[1]
=====================
[2, 1]
[1, 2]
=====================
[3, 2, 1]
[2, 3, 1]
[2, 1, 3]
[3, 1, 2]
[1, 3, 2]
[1, 2, 3]
=====================
 */
    public List<List<Integer>> permuteV3(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length == 0) return result;
        result.add(new ArrayList<>());
        result.get(0).add(nums[0]);
//        System.out.println("=====================");
//        Helpers.print2D(result);
        for(int i = 1; i < nums.length; i++) {
            List<List<Integer>> tempResult = new ArrayList<>();
            for (List<Integer> temp : result) {
                for (int k = 0; k <= temp.size(); k++) {
                    List<Integer> permute = new ArrayList<>(temp);
                    permute.add(k, nums[i]);
                    tempResult.add(permute);
                }
            }
//            System.out.println("=====================");
//            Helpers.print2D(tempResult);
            result = tempResult;
        }
        return result;
    }
}
