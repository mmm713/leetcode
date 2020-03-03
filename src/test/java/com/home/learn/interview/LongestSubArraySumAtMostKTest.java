package com.home.learn.interview;

import com.home.learn.leetcode.LongestSubArraySumAtMostK;
import org.junit.jupiter.api.Test;


class LongestSubArraySumAtMostKTest {
    @Test
    void test(){
        int[] arr = { 1, 2, 1, 0, 1, 1, 0 };
        int n = arr.length;
        int k = 4;
        LongestSubArraySumAtMostK ans = new LongestSubArraySumAtMostK();
        System.out.println("===================");
        System.out.println(ans.atMostSum(arr, n, k));
    }
}
