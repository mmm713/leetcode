package com.home.learn.smartnews;

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
        for(int i = 0; i < result.length; i++){
            result[i] = original[i];
        }
        for(int i = 0; i < result.length - 1; i++){
            int index = i + (int)(Math.random() * (result.length - i));
            int temp = result[index];
            result[index] = result[i];
            result[i] = temp;
        }
        return result;
    }
}
