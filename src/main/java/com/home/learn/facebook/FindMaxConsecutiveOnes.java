package com.home.learn.facebook;

public class FindMaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        //flip at most 1
        int longestSequence = 0;
        int left = 0;
        int right = 0;
        int numZeroes = 0;

        while (right < nums.length) {
            if (nums[right] == 0) {
                numZeroes++;
            }
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

    public int longestOnes(int[] A, int K) {
        //sliding window
        int left = 0, right = 0;
        while (right < A.length) {
            if (A[right] == 0) K--;
            if (K < 0) {
                if (A[left] == 0) K++;
                left++;
            }
            right++;
        }
        return right - left;
    }
}
