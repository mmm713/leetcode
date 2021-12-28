package com.home.learn.amazon;

import org.junit.jupiter.api.Test;

public class SumSubarrayMinimumsTest {
    @Test
    void test() {
        int[] test = {3,1,2,4};
        SumSubarrayMinimums minimums = new SumSubarrayMinimums();
        int res = minimums.sumSubarrayMinsV1(test);
        System.out.println(res);
    }
    @Test
    void test1() {
        int[] test = {10, 3, 4, 5, 3, 2, 3, 10};
        SumSubarrayMinimums minimums = new SumSubarrayMinimums();
        int res = minimums.sumSubarrayMinsV1(test);
        System.out.println(res);
    }
    @Test
    void test2() {
        int[] test = {10, 3, 4, 5, 3, 2, 3, 10};
        SumSubarrayMinimums minimums = new SumSubarrayMinimums();
        int res = minimums.sumSubarrayMinsV2(test);
        System.out.println(res);
    }
    @Test
    void test31() {
        int[] test = {11,81,94,43,3};
        SumSubarrayMinimums minimums = new SumSubarrayMinimums();
        int res = minimums.sumSubarrayMinsV1(test);
        System.out.println(res);
    }
    @Test
    void test32() {
        int[] test = {11,81,94,43,3};
        SumSubarrayMinimums minimums = new SumSubarrayMinimums();
        int res = minimums.sumSubarrayMinsV2(test);
        System.out.println(res);
    }
}
