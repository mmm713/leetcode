package com.home.learn.bytedance;

public class CustomSortString {
    public String customSortString(String order, String str) {
        int[] hash = new int[128];
        for(char c : str.toCharArray()) {
            hash[c - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        for(char c : order.toCharArray()) {
            while(hash[c - 'a']-- > 0) {
                sb.append(c);
            }
        }
        for(int i = 0; i < hash.length; i++) {
            while(hash[i]-- > 0) {
                sb.append((char)('a' + i));
            }
        }
        return sb.toString();
    }
}
