package com.home.learn.facebook;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int nz = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                swap(nums, nz++, i);
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
