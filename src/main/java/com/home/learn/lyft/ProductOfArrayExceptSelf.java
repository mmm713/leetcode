package com.home.learn.lyft;

import java.util.Arrays;

public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int[] answer = new int[length];
        Arrays.fill(answer, 1);
        int right = 1;
        int left = 1;
        for (int i = 0; i < length; i++) {
            answer[i] = (i == 0) ? 1 : answer[i] * left;
            answer[length - i - 1] = (i == 0) ? 1 : answer[length - i - 1] * right;
            left *= nums[i];
            right *= nums[length - i - 1];
        }
        return answer;
    }
}
