package com.home.learn;

import com.home.learn.microsoft.JumpGame;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JumpGameTest {
    @Test
    void test4() {
        JumpGame jumpGame = new JumpGame();
        int[] input = {100,-23,-23,404,100,23,23,23,3,404};
        assertEquals(3, jumpGame.minJumps(input));
    }
    @Test
    void test41() {
        JumpGame jumpGame = new JumpGame();
        int[] input = {7,7,2,1,7,7,7,3,4,1};
        assertEquals(3, jumpGame.minJumps(input));
    }
}
