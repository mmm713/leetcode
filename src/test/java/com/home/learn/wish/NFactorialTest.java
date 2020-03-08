package com.home.learn.wish;

import org.junit.jupiter.api.Test;

class NFactorialTest {
    private static final String OUT_FORMAT = "Counting Zero for n : %d, brute force: %d, smart: %d ";
    @Test
    void test() {
        NFactorial factorial = new NFactorial();
        int n = 6;
        System.out.println(String.format(OUT_FORMAT, factorial.getFactorial(n), factorial.findTrailingZerosII(n), factorial.findTrailingZeros(n)));
        n = 20;
        System.out.println(String.format(OUT_FORMAT, factorial.getFactorial(n), factorial.findTrailingZerosII(n), factorial.findTrailingZeros(n)));
    }
}
