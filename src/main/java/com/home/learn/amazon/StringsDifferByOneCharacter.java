package com.home.learn.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringsDifferByOneCharacter {
    public boolean differByOne(String[] dict) {
        long mod = (long) Math.pow(10, 20) + 7;

        int len = dict[0].length();
        long[] word2hash = new long[dict.length];
        for (int i = 0; i < dict.length; i++) {
            for (int j = 0; j < len; j++) {
                word2hash[i] = (word2hash[i] * 26 + dict[i].charAt(j) - 'a') % mod;
            }
        }

        long base = 1;
        for (int j = len - 1; j >= 0; j--) {
            Map<Long, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < dict.length; i++) {
                long newHash = (word2hash[i] - base * (dict[i].charAt(j) - 'a')) % mod;
                if (map.containsKey(newHash)) {
                    return true;
                }
                map.putIfAbsent(newHash, new ArrayList<>());
                map.get(newHash).add(i);
            }
            base = 26 * base % mod;
        }

        return false;
    }
}
