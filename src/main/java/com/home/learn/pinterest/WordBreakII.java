package com.home.learn.pinterest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBreakII {
    //复杂度 n^3
    public List<String> WordBreakII(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        int max = 0;
        boolean[] possible = new boolean[s.length() + 1];
        Arrays.fill(possible, true);
        for (String value : wordDict) {
            max = Math.max(max, value.length());
        }
        wordBreakDFS(s, wordDict, 0, max, "", result, possible);
        return result;
    }

    private void wordBreakDFS(String s, List<String> wordDict, int start, int max, String toAdd, List<String> result, boolean[] possible) {
        if(start == s.length()) {
            result.add(toAdd.substring(1));
            return;
        }
        for(int i = 0; i < max; i++) {
            if(start + i >= s.length()) {
                break;
            }
            String substring = s.substring(start, start + i + 1);
            if(wordDict.contains(substring) && possible[start + i + 1]) {
                int oldSize = result.size();
                wordBreakDFS(s, wordDict, start + i + 1, max,
                        toAdd + " " + substring, result, possible);
                if(result.size() == oldSize) possible[start + i + 1] = false;
            }
        }
    }
}
