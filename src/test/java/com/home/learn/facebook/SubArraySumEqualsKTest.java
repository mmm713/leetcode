package com.home.learn.facebook;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubArraySumEqualsKTest {

    @Test
    void test() {
        SubArraySumEqualsK k = new SubArraySumEqualsK();
        int[] n0 = {1, 3, 1, 4, 23};
        assertTrue(k.subarraySumPositive(n0, 8));
        assertFalse(k.subarraySumPositive(n0, 7));
        int[] n1 = {500, 1, 3, 1, 4, 23};
        assertTrue(k.subarraySumPositive(n1, 8));
        assertFalse(k.subarraySumPositive(n1, 7));
        int[] n2 = {1, 4, 500, 1, 3, 1, 4, 23};
        assertTrue(k.subarraySumPositive(n2, 8));
        assertFalse(k.subarraySumPositive(n2, 7));
        assertTrue(k.subarraySumPositive(n2, 23));
        assertTrue(k.subarraySumPositive(n2, 27));
        assertTrue(k.subarraySumPositive(n2, 1));
        assertTrue(k.subarraySumPositive(n2, 505));
        assertTrue(k.subarraySumPositive(n2, 504));
        assertTrue(k.subarraySumPositive(n2, 532));
    }
}
