package com.home.learn.doordash;

public class MissingNumber {
    public int missingNumber(int[] nums) {
        int sum = 0;
        for(int i = 0; i <= nums.length; i++) {
            sum += i;
            if(i < nums.length) {
                sum -= nums[i];
            }
        }
        return sum;
    }
}
