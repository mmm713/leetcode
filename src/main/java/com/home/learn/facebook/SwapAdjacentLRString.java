package com.home.learn.facebook;

public class SwapAdjacentLRString {
    public boolean canTransform(String start, String end) {
        char[] s = start.toCharArray();
        char[] e = end.toCharArray();
        int n = s.length, cntL = 0, cntR = 0;
        for (int i = 0; i < n; ++i) {
            if (s[i] == 'R') ++cntR;
            if (e[i] == 'L') ++cntL;
            if (s[i] == 'L') --cntL;
            if (e[i] == 'R') --cntR;
            if (cntL < 0 || cntR < 0 || cntL * cntR != 0) return false;
        }
        return cntL == 0 && cntR == 0;
    }
}
