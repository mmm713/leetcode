package com.home.learn.leetcode.dp;

public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        int si = 0, pi = 0, sstar = -1, pstar = -1;
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        while(si < sc.length) {
            if(pi < p.length() && (sc[si] == pc[pi] || pc[pi] == '?')) {
                si++;
                pi++;
            } else if (pi < p.length() && pc[pi] == '*') {
                pstar = pi++;
                sstar = si;
                System.out.println(sstar);
            } else if (pstar > -1) {
                pi = pstar + 1;
                si = ++sstar;
            } else return false;
        }
        while (pi < pc.length && pc[pi] == '*') ++pi;
        return (pi == pc.length);
    }

    public boolean isMatchV1(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        if (s.isEmpty()) {
            int i = 0;
            while(i < p.length() && p.charAt(i) == '*') {
                i++;
            }
            return i == p.length();
        }
        if (p.charAt(0) == '*') {
            return isMatch(s, p.substring(1)) || isMatch(s.substring(1), p);
        } else {
            return matchSingle(s, p, 0, 0) && isMatch(s.substring(1), p.substring(1));
        }
    }
    private boolean matchSingle(String s, String p, int i, int j) {
        return !s.isEmpty() && (i >= 0) && (j >= 0) && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?');
    }
}
