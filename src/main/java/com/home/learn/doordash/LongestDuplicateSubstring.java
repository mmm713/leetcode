package com.home.learn.doordash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestDuplicateSubstring {

    public String longestDupSubstring(String s) {
        int length = s.length();
        int[] nums = new int[length];
        for (int i = 0; i < length; i++) {
            nums[i] = s.charAt(i) - 'a';
        }

        //二分确定待查子串长度，若当前子串可以查出重复，则left右移，若不能则right左移
        int left = 1, right = length, end = -1, mid, temp;
        while (left < right) {
            mid = left + (right - left) / 2;
            temp = search(mid, nums);
            if (search(mid, nums) != -1) {
                end = temp;
                left = mid + 1;
            }
            else right = mid;
        }
        return end != -1 ? s.substring(end - left + 2, end + 1) : "";

    }

    public int search(int length, int[] nums) {
        Map<Long, List<Integer>> seen = new HashMap<>();
        //first是第一个子串[0, length)的hash值
        long first = nums[0];
        //highest = 26^(length-1)，用于后续计算其他子串的hash
        long highest = 1;
        long mod = Integer.MAX_VALUE;
        for (int i = 1; i < length; i++) {
            first = (first * 26 + nums[i]) % mod;
            highest = (highest * 26) % mod;
        }
        List<Integer> list = new ArrayList<>();
        list.add(length - 1);
        seen.put(first, list);

        long hash = first;
        for (int i = length; i < nums.length; i++) {
            //最后加一个mod是考虑到前面的值可能是负数
            hash = (hash * 26 - highest * nums[i - length] * 26 % mod + nums[i] + mod) % mod;
            List<Integer> contains = seen.getOrDefault(hash, new ArrayList<>());
            for(int index : contains){
                if(check(index, i, length, nums)){
                    return i;
                }
            }
            contains.add(i);
            seen.put(hash, contains);
        }
        return -1;
    }

    private boolean check(int begin1, int begin2, int length, int[] nums) {
        for(int i = 0; i < length; i++){
            if(nums[begin1 - i] != nums[begin2 - i]){
                return false;
            }
        }
        return true;
    }
}
