package com.home.learn.facebook;

import java.util.ArrayList;
import java.util.List;

public class PartitionLabels {

    public List<Integer> partitionLabels(String S) {
        int[] hash = new int[26];
        char[] sc = S.toCharArray();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < S.length(); ++i) {
            hash[sc[i] - 'a'] = i;
        }
        int max = Integer.MIN_VALUE, start = -1;
        for (int i = 0; i < S.length(); ++i) {
            max = Math.max(max, hash[sc[i] - 'a']);
            if (i == max) {
                ans.add(i - start);
                start = i;
            }
        }
        return ans;
    }
}
