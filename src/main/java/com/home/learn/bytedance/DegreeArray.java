package com.home.learn.bytedance;

import java.util.Arrays;

public class DegreeArray {
    public int findShortestSubArray(int[] nums) {
        int max = Arrays.stream(nums).max().getAsInt();
        int[] degree = new int[max+1];
        int[] start = new int[max+1];
        int[] end = new int[max+1];
        int d = 0;
        for(int i = 0; i < nums.length; i++){
            degree[nums[i]]++;
            d = Math.max(d, degree[nums[i]]);
            if(start[nums[i]] == 0){
                start[nums[i]] = i+1;
            }
            end[nums[i]] = i+1;
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < degree.length; i++){
            if(degree[i] == d){
                res = Math.min(res, end[i] - start[i] + 1);
            }
        }
        return res;
    }
}
