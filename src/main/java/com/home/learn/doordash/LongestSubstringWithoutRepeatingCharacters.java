package com.home.learn.doordash;

import java.util.Arrays;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int[] hash = new int[128];
        Arrays.fill(hash, -1);
        int result = 0, end = 0, start = 0;
        char[] sc = s.toCharArray();
        for(int i = 0; i < s.length(); i++) {
            if(hash[sc[i]] >= 0 && hash[sc[i]] >= start) {
                start = hash[sc[i]] + 1;
                end = i - hash[sc[i]];
            } else {
                end++;
            }
            result = Math.max(result, end);
            hash[sc[i]] = i;
        }
        return result;
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(s.length() ==0 || k==0) return 0;
        int[] hash = new int[256];
        int maxLen = 0;
        int start = 0, num = 0;
        char[] sc = s.toCharArray();
        for(int i = 0; i < s.length(); i++) {
            //expand window
            if(hash[sc[i]] == 0) {
                num++;
            }
            hash[sc[i]]++;
            //contract window
            while(num > k && start < i){
                hash[s.charAt(start)]--;
                if(hash[s.charAt(start)] == 0) num--;
                start++;
            }
            maxLen = Math.max(maxLen, i - start + 1);
        }
        return maxLen;
    }
}
