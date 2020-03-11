package com.home.learn.pinterest;

import org.junit.jupiter.api.Test;

class MinimumWindowSubsequenceTest {
    @Test
    void test() {
        MinimumWindowSubsequence subsequence = new MinimumWindowSubsequence();
        subsequence.minWindow("abcdebdbde", "bde");
        System.out.println("=====================");
        subsequence.minWindowII("abcdebdbde", "bde");
    }
    @Test
    void test1() {
        MinimumWindowSubsequence subsequence = new MinimumWindowSubsequence();
        subsequence.minWindow("cnhczmccqouqadqtmjjzl", "mm");
        System.out.println("=====================");
        subsequence.minWindowII("cnhczmccqouqadqtmjjzl", "mm");
    }
}
