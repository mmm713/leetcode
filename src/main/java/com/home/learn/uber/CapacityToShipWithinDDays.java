package com.home.learn.uber;

import java.util.Arrays;

public class CapacityToShipWithinDDays {
    public int shipWithinDays(int[] weights, int D) {
        int right = Arrays.stream(weights).sum();
        int left = Arrays.stream(weights).max().getAsInt();
        while(left < right) {
            int middle = left + (right - left) / 2;
            if(targetSmall(weights, D, middle)) {
                left = middle + 1;
            } else {
                right = middle;
            }
        }
        return left;
    }

    private boolean targetSmall(int[] weights, int D, int target) {
        int sum = 0;
        for(int weight : weights) {
            if(sum + weight <= target) {
                sum += weight;
            } else {
                if(--D <= 0) {
                    return true;
                }
                sum = weight;
            }
        }
        return false;
    }
}
