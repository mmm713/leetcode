package com.home.learn.facebook;

public class RotateArray {
    public void rotate(int[] nums, int k) {
        k %= nums.length;

        rotate(nums,0,nums.length-1);
        rotate(nums,0,k-1);
        rotate(nums, k,nums.length-1);
    }

    public void rotate(int[] array, int start, int end){
        while(start < end){
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while(end >= start) {
            int middle = start + (end - start) / 2;
            if(target == nums[middle])
                return middle;
            if(nums[middle] >= nums[start]){
                if(target >= nums[start] && target < nums[middle]) {
                    end = middle - 1;
                } else {
                    start = middle + 1;
                }
            } else {
                if(target <= nums[end] && target > nums[middle]) {
                    start = middle + 1;
                } else {
                    end = middle - 1;
                }
            }
        }
        return -1;
    }
}
