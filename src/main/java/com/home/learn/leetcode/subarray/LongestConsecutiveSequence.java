package com.home.learn.leetcode.subarray;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Set<Integer> hash = new HashSet<>();
        for (int num : nums) hash.add(num);
        int result = 0;
        for(int num : nums){
            if(hash.remove(num)){
                int pre = num - 1, next = num + 1;
                while (hash.remove(pre)) --pre;
                while (hash.remove(next)) ++next;
                result = Math.max(result, next - pre - 1);
            }
        }
        return result;
    }
}
