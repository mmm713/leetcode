package com.home.learn.leetcode;

import java.util.*;

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> resultList = new ArrayList<>();
        for(int i = 0; i < nums.length - 3; i++) {
            if( i > 0 && nums[i] == nums[i - 1] ) // remove duplicate
                continue;
            if( nums[i] + 3 * nums[nums.length - 1] < target ) // current num is too small
                continue;
            if( nums[i] * 4 > target ) // current num is too large
                break;
            for(int j = i + 1; j < nums.length - 2; j++) {
                if( j > i + 1 && nums[j] == nums[j - 1] ) // duplicate removal
                    continue;
                if( nums[i] + nums[j] + 2 * nums[nums.length - 1] < target ) // current num is too small
                    continue;
                if( nums[i] + nums[j] * 3 > target ) // current num is too large
                    break;
                int begin = j + 1, end = nums.length - 1;
                while( begin < end ) {
                    int sum = nums[i] + nums[j] + nums[begin] + nums[end];
                    if( sum > target )
                        end--;
                    else if( sum < target )
                        begin++;
                    else {
                        if( begin == j + 1 || nums[begin] != nums[begin - 1] ) {
                            List<Integer> currentList = new ArrayList<>();
                            currentList.add(nums[i]);
                            currentList.add(nums[j]);
                            currentList.add(nums[begin]);
                            currentList.add(nums[end]);
                            resultList.add(currentList);
                        }
                        begin++;
                        end--;
                    }
                }
            }
        }
        return resultList;
    }

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int a : A) {
            for(int b : B){
                int freq = map.getOrDefault(a + b, 0);
                map.put(a + b, freq + 1);
            }
        }
        int count = 0;
        for(int c : C) {
            for(int d : D) {
                count += map.getOrDefault(-(c + d), 0);
            }
        }
        return count;
    }
}
