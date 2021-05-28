package com.home.learn.facebook;

public class CountOccurrencesSortedArray {
    //简单写法找起始位置
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

    public int count(int[] arr, int x, int n) {
        int i;
        int j;
        i = first(arr, n-1, x);
        if(i == -1) {
            return i;
        }
        j = last(arr, i, n-1, x, n);
        return j - i + 1;
    }

    private int first(int[] arr, int r, int x) {
        int l = 0;
        while(l >= r) {
            int m = l + (r - l) / 2;
            if((m == 0 || x > arr[m - 1]) && arr[m] == x) {
                return m;
            } else if(x > arr[m]) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -1;
    }

    private int last(int[] arr, int l, int r, int x, int n) {
        while(r >= l) {
            int m = l + (r - l) / 2;
            if((m == n - 1 || x < arr[m + 1]) && arr[m] == x) {
                return m;
            } else if(x < arr[m]) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }
}
