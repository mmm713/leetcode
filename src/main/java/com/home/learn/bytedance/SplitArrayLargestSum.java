package com.home.learn.bytedance;

import java.util.Arrays;

public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        int l = Arrays.stream(nums).max().getAsInt();
        int r = Arrays.stream(nums).sum();
        while(l < r) {
            int mid = l + (r - l) / 2;
            int cnt = findCount(nums, mid);
            if(cnt > m) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
    private int findCount(int[] nums, int mid) {
        int sum = 0;
        int cnt = 1;
        for(int n : nums) {
            if(sum + n > mid) {
                cnt++;
                sum = n;
            } else {
                sum += n;
            }
        }
        return cnt;
    }
}
