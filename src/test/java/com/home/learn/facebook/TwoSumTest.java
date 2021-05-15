package com.home.learn.facebook;

import com.home.learn.leetcode.TwoSum;
import org.junit.jupiter.api.Test;

public class TwoSumTest {
    @Test
    void test(){
        TwoSum twoSum = new TwoSum();
        int[] n0 = {1, 2, 3, 4, 3};
        int[] n1 = {1, 5, 3, 3, 3};
        int[] n2 = {1, 5, 3, 3, 3, 4, 7, 4, 9, 8, 3, 6, 6};
        System.out.println(twoSum.numberOfWays(n0, 6));
        System.out.println(twoSum.numberOfWays(n1, 4));
        System.out.println(twoSum.numberOfWays(n2, 9));
        System.out.println(twoSum.numberOfWays(n2, 6));
    }
}
