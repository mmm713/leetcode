package com.home.learn.google;

public class FindMinimumInRotatedSortedArray {
    //没有重复
    public int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;
        if(nums.length == 1) return nums[0];
        while(end > start) {
            if(nums[end] > nums[start]) {
                return nums[start];
            }
            int middle = start + (end - start) / 2;
            if(nums[middle] < nums[end]) {
                end = middle;
            } else {
                start = middle + 1;
            }
        }
        return nums[start];
    }
    //第二问有重复。
    public int findMinII(int[] nums) {
        int start = 0, end = nums.length - 1;
        if(nums.length == 1) return nums[0];
        while(end > start) {
            if(nums[end] > nums[start]) {
                return nums[start];
            }
            int middle = start + (end - start) / 2;
            if(nums[start] == nums[middle]){
                start++;
            } else if(nums[end] == nums[middle]){
                end--;
            } else if(nums[middle] < nums[end]) {
                end = middle;
            } else if(nums[middle] > nums[end]) {
                start = middle + 1;
            }
        }
        return nums[start];
    }
}
