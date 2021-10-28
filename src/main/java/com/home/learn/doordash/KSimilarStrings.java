package com.home.learn.doordash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KSimilarStrings {
    public int kSimilarity(String s1, String s2) {
        int n = s1.length(), res = n - 1;
        char[] s1c = s1.toCharArray();
        char[] s2c = s2.toCharArray();
        for (int i = 0; i < n; i++) {
            if (s1c[i] == s2c[i]) continue;
            List<Integer> matches = new ArrayList<>();
            for (int j = i + 1; j < n; j++) {
                if (s1c[j] == s2c[j] || s1c[j] != s2c[i]) continue;
                matches.add(j);
                if (s1c[i] != s2c[j]) continue;
                swap(s1c, i, j);
                return 1 + kSimilarity(new String(s1c).substring(i + 1), new String(s2c).substring(i + 1));
            }
            for (int j : matches) {
                swap(s1c, i, j);
                res = Math.min(res, 1 + kSimilarity(new String(s1c).substring(i + 1), new String(s2c).substring(i + 1)));
                swap(s1c, i, j);
            }
            return res;
        }
        return 0;
    }

    public int kSimilarityBest(String s1, String s2) {
        return helper(s1.toCharArray(), s2.toCharArray(), 0);
    }

    private int helper(char[] s1c, char[] s2c, int i) {
        int res = s1c.length - 1;
        while (i < s1c.length && s1c[i] == s2c[i]) {
            ++i;
        }
        if (i < s1c.length - 1) {
            List<Integer> matches = new ArrayList<>();
            for (int j = i + 1; j < s1c.length; j++) {
                if (s1c[j] == s2c[j] || s1c[j] != s2c[i]) continue;
                matches.add(j);
                if (s1c[i] != s2c[j]) continue;
                swap(s1c, i, j);
                int temp = 1 + helper(s1c, s2c, i);
                swap(s1c, i, j);
                return temp;
            }
            for (int j : matches) {
                swap(s1c, i, j);
                res = Math.min(res, 1 + helper(s1c, s2c, i));
                swap(s1c, i, j);
            }
            return res;
        }
        return 0;
    }

    private void swap(char[] s, int i, int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }


    public int kSimilarityDFS(String s1, String s2) {
        Map<String, Integer> hash = new HashMap<>();
        return dfs(s1.toCharArray(), s2.toCharArray(), 0, hash);
    }

    private int dfs(char[] s1, char[] s2, int i, Map<String, Integer> hash) {
        String s1s = new String(s1);
        if (hash.containsKey(s1s)) {
            return hash.get(s1s);
        }
        int res = Integer.MAX_VALUE;
        while (i < s1.length && s1[i] == s2[i]) {
            ++i;
        }
        if(i == s1.length) {
            return 0;
        }
        for (int j = i + 1; j < s1.length; ++j) {
            if (s1[j] == s2[j] || s1[j] != s2[i]) continue;
            swap(s1, i, j);
            int next = dfs(s1, s2, i + 1, hash);
            if (next != Integer.MAX_VALUE) {
                res = Math.min(res, next + 1);
            }
            swap(s1, i, j);
        }
        hash.put(new String(s1), res);
        return res;
    }
}
