package com.home.learn.interview;

import com.home.learn.leetcode.WallsAndGates;
import org.junit.jupiter.api.Test;

class WallsAndGatesTest {
    @Test
    void test() {
        int[][] test = {{2147483647,-1,0,2147483647},{2147483647,2147483647,2147483647,-1},
                {2147483647,-1,2147483647,-1},{0,-1,2147483647,2147483647}};
        WallsAndGates wallsAndGates = new WallsAndGates();
        wallsAndGates.wallsAndGates(test);
    }
}
