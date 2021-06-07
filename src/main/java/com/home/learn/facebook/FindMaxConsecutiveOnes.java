package com.home.learn.facebook;

public class FindMaxConsecutiveOnes {
    //最多可以翻1个0，双指针贪婪求解，一旦有2个0，挪动左指针直到剔除
    public int findMaxConsecutiveOnes(int[] nums) {
        //flip at most 1
        int longestSequence = 0;
        int left = 0;
        int right = 0;
        int numZeroes = 0;

        while (right < nums.length) {
            if (nums[right] == 0) numZeroes++;
            while (numZeroes == 2) {
                if (nums[left] == 0) {
                    numZeroes--;
                }
                left++;
            }
            longestSequence = Math.max(longestSequence, right - left + 1);
            right++;
        }
        return longestSequence;
    }

    //最多可以翻K个。其实每当K不够时，直接挪动window即可，window可以增长就让他增长，不能增长其实就是答案了
    public int longestOnes(int[] nums, int K) {
        //sliding window
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] == 0) K--;
            if (K < 0) {
                if (nums[left] == 0) K++;
                left++;
            }
            right++;
        }
        return right - left;
    }
}
