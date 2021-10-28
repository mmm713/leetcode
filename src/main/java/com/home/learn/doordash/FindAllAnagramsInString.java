package com.home.learn.doordash;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        char[] ss = s.toCharArray();
        char[] pp = p.toCharArray();
        int count = pp.length;
        int[] counts = new int[256];
        for (char c : pp) {
            counts[c]++;
        }
        int i = 0;
        for (int j = 0; j < ss.length; j++) {
            if (counts[ss[j]] > 0) {
                count--;
            }
            counts[ss[j]]--;
            while (count == 0) {
                if (j - i + 1 == pp.length) {
                    result.add(i);
                }
                counts[ss[i]]++;
                if (counts[ss[i]] > 0) {
                    count++;
                }
                i++;
            }
        }
        return result;

    }
}
