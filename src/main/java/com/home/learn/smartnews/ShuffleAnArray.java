package com.home.learn.smartnews;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ShuffleAnArray {

    int[] original;
    public ShuffleAnArray(int[] nums) {
        original = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return original;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] result = new int[original.length];
        System.arraycopy(original, 0, result, 0, result.length);
        for(int i = 0; i < result.length - 1; i++){
            int index = i + (int)(Math.random() * (result.length - i));
            int temp = result[index];
            result[index] = result[i];
            result[i] = temp;
        }
        PriorityQueue<Integer> min = new PriorityQueue<>(Comparator.reverseOrder());
        return result;
    }
}
