package com.home.learn.amazon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountDecreasingRatingsTest {
    @Test
    void test() {
        CountDecreasingRatings cdr = new CountDecreasingRatings();
        int[] test0 = {4,3,5,4,3};
        int[] test1 = {2,1,3};
        int[] test2 = {4,2,3,1};
        assertEquals(9, cdr.countDecreasingRatings(test0));
        assertEquals(4, cdr.countDecreasingRatings(test1));
        assertEquals(4, cdr.countDecreasingRatings(test2));
    }

}
