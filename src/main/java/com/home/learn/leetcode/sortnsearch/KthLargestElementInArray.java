package com.home.learn.leetcode.sortnsearch;

public class KthLargestElementInArray {
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, k, 0, nums.length - 1);
    }
    //快排最好n，最差n方
    private int quickSelect(int[] nums, int k, int left, int right)
    {
        int i = left, j = right, temp, pivot = nums[left + (right - left) / 2];
        if(j - i == 1) {
            if(j == nums.length - k)  return Math.max(nums[j], nums[i]);
            else  return Math.min(nums[j], nums[i]);
        } else if(j == i)  return nums[i];
        while(i <= j) {
            while(nums[i] < pivot) {
                i++;
            }
            while(nums[j] > pivot) {
                j--;
            }
            if(i <= j) {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }
        if((i - j == 2) && (i - 1 == nums.length - k))  return pivot;
        if(j >= (nums.length - k))  return quickSelect(nums, k, left, j);
        else return quickSelect(nums, k, i, right);
    }
}
