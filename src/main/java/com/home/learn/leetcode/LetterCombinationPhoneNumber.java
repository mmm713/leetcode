package com.home.learn.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationPhoneNumber {
    private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        if(digits == null || digits.length() == 0) return result;
        combination("", digits, 0, result);
        return result;
    }
    private void combination(String prefix, String digits, int offset, List<String> ret) {
        if (offset >= digits.length()) {
            ret.add(prefix);
            return;
        }
        KEYS[(digits.charAt(offset) - '0')].chars().forEach(s -> {
            combination(prefix.concat(Character.toString((char) s)), digits, offset + 1, ret);
        });
    }
}
