package com.home.learn.pinterest;

public class MinimumWindowSubstring {
    public static String minWindow(String s, String t) {
        int[] hash = new int[128];
        for(char c : t.toCharArray()) {
            hash[c]++;
        }
        int left = 0, counter = t.length(), window = Integer.MAX_VALUE;
        int start = 0;
        for(int i = 0; i < s.length(); i++) {
            if(--hash[s.charAt(i)] >= 0) {
                counter--;
            }
            while(counter <= 0) {
                if(i - left < window) {
                    start = left;
                    window = i - left;
                }
                if(hash[s.charAt(left++)]++ >= 0) {
                    counter++;
                }
            }
        }
        return window == Integer.MAX_VALUE ? "" : s.substring(start, start + window + 1);
    }
}
