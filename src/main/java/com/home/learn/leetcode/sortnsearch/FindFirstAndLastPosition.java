package com.home.learn.leetcode.sortnsearch;

public class FindFirstAndLastPosition {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};

        int startIndex = search(nums, 0, nums.length-1, target, -1);
        int endIndex = search(nums, 0, nums.length-1, target, 1);
        return new int[]{startIndex, endIndex};
    }

    private int search(int[] nums, int lo, int hi, int target, int offset) {
        int newIndex;

        while (lo < hi) {
            int mid = (hi-lo)/2 + lo;
            if (nums[mid] < target) lo = mid + 1;
            else if (nums[mid] > target) hi = mid - 1;
            else { //equal target
                newIndex = mid + offset;
                if (0 <= newIndex && newIndex < nums.length && nums[newIndex] == target) {
                    if (offset == -1) hi = newIndex;
                    else lo = newIndex;
                }
                else return mid;
            }
        }

        return nums[lo] == target ? lo : -1;
    }
}
