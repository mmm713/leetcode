package com.home.learn.uber;

import org.junit.jupiter.api.Test;

public class CapacityToShipWithinDDaysTest {
    @Test
    void test1() {
        CapacityToShipWithinDDays dDays = new CapacityToShipWithinDDays();
        int[] test = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(dDays.shipWithinDays(test, 5));
    }
    @Test
    void test2() {
        CapacityToShipWithinDDays dDays = new CapacityToShipWithinDDays();
        int[] test = {1,2,3,1,1};
        System.out.println(dDays.shipWithinDays(test, 4));
    }
}
