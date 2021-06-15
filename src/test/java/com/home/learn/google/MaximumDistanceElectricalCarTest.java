package com.home.learn.google;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaximumDistanceElectricalCarTest {
    @Test
    void test() {
        MaximumDistanceElectricalCar car = new MaximumDistanceElectricalCar();
        int[] input = {1,1,1,9,9,9};
        int res = car.maxDistanceDfs(input, 3);
        System.out.println(res);
        assertEquals(res, car.maxDistanceDfs(input, 3));
    }
    @Test
    void test1() {
        MaximumDistanceElectricalCar car = new MaximumDistanceElectricalCar();
        int[] input = {3, 5, 6, 8, 9, 3, 4, 7, 3, 2, 8};
        int res = car.maxDistanceDfs(input, 3);
        System.out.println(res);
        assertEquals(res, car.maxDistanceDfs(input, 3));
    }

    @Test
    void test2() {
        MaximumDistanceElectricalCar car = new MaximumDistanceElectricalCar();
        int[] input = {3, 5, 6};
        int res = car.maxDistanceDfs(input, 3);
        System.out.println(res);
        assertEquals(res, car.maxDistanceDfs(input, 3));
    }

    @Test
    void test3() {
        MaximumDistanceElectricalCar car = new MaximumDistanceElectricalCar();
        int[] input = {3, 5, 6};
        int res = car.maxDistanceDfs(input, 9);
        System.out.println(res);
        assertEquals(res, car.maxDistanceDfs(input, 9));
    }

    @Test
    void test4() {
        MaximumDistanceElectricalCar car = new MaximumDistanceElectricalCar();
        int[] input = {3, 5, 6};
        int res = car.maxDistanceDfs(input, 9);
        System.out.println(res);
        assertEquals(res, car.maxDistanceDfs(input, 9));
    }

    @Test
    void test5() {
        MaximumDistanceElectricalCar car = new MaximumDistanceElectricalCar();
        int[] input = {1};
        int res = car.maxDistanceDfs(input, 9);
        System.out.println(res);
        assertEquals(res, car.maxDistanceDfs(input, 9));
    }
}
