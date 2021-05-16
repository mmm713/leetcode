package com.home.learn.leetcode.subarray;

public class MaximumSumCircularSubarray {
    public int maxSubarraySumCircular(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int maxSoFar = nums[0];
        int currMax = nums[0];
        for(int i = 1; i < nums.length; i++){
            currMax = Math.max(nums[i], nums[i] + currMax);
            maxSoFar = Math.max(maxSoFar, currMax);
        }
        int minSoFar = nums[0];
        int currMin = nums[0];
        for(int i = 1; i < nums.length; i++){
            currMin = Math.min(nums[i],nums[i] + currMin);
            minSoFar = Math.min(minSoFar, currMin);
        }
        if(sum == minSoFar){
            return maxSoFar;
        }
        return Math.max(maxSoFar, sum - minSoFar);
    }

    public int maxSubarraySumCircularV2(int[] A) {
        int total = 0, maxSum = A[0], curMax = 0, minSum = A[0], curMin = 0;
        for (int a : A) {
            curMax = Math.max(curMax + a, a);
            maxSum = Math.max(maxSum, curMax);
            curMin = Math.min(curMin + a, a);
            minSum = Math.min(minSum, curMin);
            total += a;
        }
        return maxSum > 0 ? Math.max(maxSum, total - minSum) : maxSum;
    }
}
