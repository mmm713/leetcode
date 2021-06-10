package com.home.learn.google;

import org.junit.jupiter.api.Test;

public class MaximumDistanceElectricalCarTest {
    @Test
    void test() {
        MaximumDistanceElectricalCar car = new MaximumDistanceElectricalCar();
        int[] input = {1,1,1,9,9,9};
        System.out.println(car.maxDistance(input, 3));
    }
}
