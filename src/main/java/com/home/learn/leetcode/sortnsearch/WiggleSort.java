package com.home.learn.leetcode.sortnsearch;

import java.util.Arrays;

public class WiggleSort {
    public int wiggleMaxLength(int[] nums) {
        int result = 1;
        if(nums.length < 1) return 0;
        boolean state = false, prev = false, curr = false;
        for(int i = 1; i < nums.length; i++) {
            curr = (nums[i] - nums[i - 1]) > 0;
            if(nums[i] != nums[i - 1]) {
                if(!state) {
                    result++;
                    prev = curr;
                    state = true;
                } else if(prev == !curr) {
                    result++;
                    prev = curr;
                }
            }
        }
        return result;
    }

    public void wiggleSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if ((i % 2 == 0) == nums[i] > nums[i + 1]) {
                System.out.println("swap happened at i = " + i);
                swap(nums, i, i + 1);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void wiggleSortII(int[] nums) {
        if(nums == null || nums.length == 0) return;
        int median = quickSelect(nums, nums.length / 2, 0, nums.length - 1);
        int n = nums.length;
        int left = 0, i = 0, right = n - 1;
        while (i <= right){
            if (nums[newIndex(i, n)] > median){
                swap(nums,newIndex(left++, n), newIndex(i++, n));
            }
            else if (nums[newIndex(i, n)] < median){
                swap(nums,newIndex(right--, n), newIndex(i, n));
            }
            else{
                i++;
            }
        }
    }
    private int newIndex(int i, int n) {
        return (1 + 2 * i) % (n | 1);
    }

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

    public void wiggleSortIII(int[] nums) {
        int[] tmp = new int[nums.length];
        System.arraycopy(nums, 0, tmp, 0, nums.length);
        Arrays.sort(tmp);
        int mid = (nums.length + 1) >> 1;
        int left = mid - 1;
        int right = nums.length - 1;
        int i = 0;
        while (left >= 0 && right >= mid) {
            nums[i++] = tmp[left--];
            nums[i++] = tmp[right--];
        }
        while (left >= 0) nums[i++] = tmp[left--];
    }
}
