package com.home.learn.pinterest;

public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int j;
        for(int i = nums.length - 2; i >= 0; i--) {
            if(nums[i + 1] > nums[i]) {
                for(j = nums.length - 1; j >= i; j--) {
                    if(nums[j] > nums[i]) break;
                }
                swap(nums, i, j);
                reverse(nums, i + 1, nums.length - 1);
                return;
            }
        }
        reverse(nums, 0, nums.length - 1);
    }

    public void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public void reverse(int[] A, int i, int j) {
        while(i < j) swap(A, i++, j--);
    }
}
