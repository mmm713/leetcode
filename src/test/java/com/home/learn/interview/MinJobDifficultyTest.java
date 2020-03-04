package com.home.learn.interview;

import com.home.learn.robinhood.MinimumDifficulityJobSchedule;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

class MinJobDifficultyTest {
    @Test
    void test() {
        MinimumDifficulityJobSchedule jobSchedule = new MinimumDifficulityJobSchedule();
        int[] test = {6,5,3,7,3,1,4};
        jobSchedule.minDifficulty(test, 4);
    }
    @Test
    void testBinarySearch() {
        int[] test = {1,3,5,5,5,7,8,10,12,14,16};
        System.out.println(Arrays.binarySearch(test, 0));
        System.out.println(Arrays.binarySearch(test, 1));
        System.out.println(Arrays.binarySearch(test, 2));
        System.out.println(Arrays.binarySearch(test, 5));
        System.out.println(Arrays.binarySearch(test, 6));
        System.out.println(Arrays.binarySearch(test, 18));
    }
}
