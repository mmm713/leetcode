package com.home.learn.smartnews;

import java.util.Arrays;

public class ValidTriangleNumber {
    public int triangleNumber(int[] nums) {
        int res = 0, n = nums.length;
        Arrays.sort(nums);
        for(int i = n - 1; i > 1; i--){
            int left = 0, right = i - 1;
            while(left < right) {
                if(nums[left] + nums[right] > nums[i]) {
                    res += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }
}
