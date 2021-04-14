package com.home.learn.google;

import org.junit.jupiter.api.Test;

public class MinimumKnightMovesTest {
    @Test
    void test(){
        MinimumKnightMoves mkm = new MinimumKnightMoves();
        System.out.println(mkm.minKnightMoves(2, 1));
    }
    @Test
    void test1(){
        MinimumKnightMoves mkm = new MinimumKnightMoves();
        System.out.println(mkm.minKnightMoves(5, 5));
    }
}
