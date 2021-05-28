package com.home.learn.facebook;

import com.home.learn.bytedance.MakeArrayStrictlyIncreasing;
import org.junit.jupiter.api.Test;

public class MakeArrayStrictlyIncreasingTest {
    @Test
    void test() {
        MakeArrayStrictlyIncreasing increasing = new MakeArrayStrictlyIncreasing();
        int[] arr1 = {1,5,3,6,7};
        int[] arr2 = {1,3,4};
        System.out.println(increasing.makeArrayIncreasing2D(arr1, arr2));
    }
    @Test
    void test1() {
        MakeArrayStrictlyIncreasing increasing = new MakeArrayStrictlyIncreasing();
        int[] arr1 = {0,11,6,1,4,3};
        int[] arr2 = {0,11,6,1,4,3};
        System.out.println(increasing.makeArrayIncreasing2DS(arr1, arr2));
    }
}
