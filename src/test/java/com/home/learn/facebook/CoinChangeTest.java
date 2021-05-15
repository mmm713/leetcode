package com.home.learn.facebook;

import org.junit.jupiter.api.Test;

public class CoinChangeTest {
    @Test
    void test() {
        CoinChange cc = new CoinChange();
        int[] coins = {1,2,5};
        System.out.println(cc.change( 5, coins));
    }
}
