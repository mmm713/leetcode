package com.home.learn.bytedance;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MinimumTimeToCompleteTripsTest {
    @Test
    public void test(){
        MinimumTimeToCompleteTrips trips = new MinimumTimeToCompleteTrips();
        int[] input = {1, 2, 3};
        long res = trips.minimumTime(input, 5);
        Assertions.assertEquals(3, res);
    }
    @Test
    public void test1(){
        MinimumTimeToCompleteTrips trips = new MinimumTimeToCompleteTrips();
        int[] input = {2};
        long res = trips.minimumTime(input, 1);
        Assertions.assertEquals(2, res);
    }
}
