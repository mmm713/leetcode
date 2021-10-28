package com.home.learn.doordash;

public class RomanToInteger {
    private static final int[] ref = new int[26];
    static {
        ref['I' - 'A'] = 1;
        ref['V' - 'A'] = 5;
        ref['X' - 'A'] = 10;
        ref['L' - 'A'] = 50;
        ref['C' - 'A'] = 100;
        ref['D' - 'A'] = 500;
        ref['M' - 'A'] = 1000;
    }

    public int romanToInt(String s) {
        int result = 0, temp = 0, prev = 10000;
        for (char c : s.toCharArray()) {
            int curr = ref[c - 'A'];
            if (curr < prev) {
                result += temp;
                temp = curr;
            } else if (prev == curr) {
                temp += curr;
            } else {
                temp = curr - temp;
            }
            prev = curr;
        }
        return result + temp;
    }
}
