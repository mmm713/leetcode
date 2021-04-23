package com.home.learn.microsoft;

import java.util.HashSet;
import java.util.Set;

public class JumpGame {
    public boolean canJump(int[] nums) {
        int lastPos = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i + nums[i] >= lastPos) {
                lastPos = i;
            }
        }
        return lastPos == 0;
    }

    public int jumpII(int[] nums) {
        int jumps = 0, coverage = 0, lastIndex = 0;
        if(nums.length <= 1){
            return 0;
        }
        for(int i = 0; i < nums.length; i++) {
            if(i > lastIndex) {
                lastIndex = coverage;
                jumps++;
            }
            if(i + nums[i] > coverage) {
                coverage = i + nums[i];
            }
            if(coverage >= nums.length - 1) {
                return ++jumps;
            }
        }
        return jumps;
    }

    public boolean canReach(int[] arr, int start) {
        if (start >= 0 && start < arr.length && arr[start] >= 0) {
            if (arr[start] == 0) {
                return true;
            }
            arr[start] = -arr[start];
            return canReach(arr, start + arr[start]) || canReach(arr, start - arr[start]);
        }
        return false;
    }
}
