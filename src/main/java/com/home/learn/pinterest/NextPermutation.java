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


    public int[] prevPermOpt1(int[] arr) {
        int n = arr.length;
        int i = n-1;
        while(i > 0 && arr[i-1] <= arr[i]){
            i--;
        }

        // if we have reached the first element then there is no
        // smaller number possible.
        if(i == 0) {
            return arr;
        }
        // Otherwise we start looking for element just smaller then i-1 element.
        int s = arr[i-1];
        int t = i;
        for(int j = i; j < n; j++) {
            if(s > arr[j] && arr[t] < arr[j]){
                t = j;
            }
        }
        swap(arr, i-1, t);
        return arr;
    }
}
