package com.home.learn.leetcode;

import com.home.learn.leetcode.subarray.ShortestSubarraySumAtLeastK;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShortestSubarraySumAtLeastKTest {
    @Test
    void test() {
        ShortestSubarraySumAtLeastK leastK = new ShortestSubarraySumAtLeastK();
        int[] input = {7, -9, -10};
        int ans = leastK.shortestSubarray(input, 4);
        assertEquals(4, ans);
    }
}
