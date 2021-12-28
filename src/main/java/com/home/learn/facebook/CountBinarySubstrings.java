package com.home.learn.facebook;

public class CountBinarySubstrings {
    public int countBinarySubstrings(String s) {
        char[] sc = s.toCharArray();
        int res = 0, counter = 1, old = 0;
        for(int i = 1; i < sc.length; i++) {
            if(sc[i] == sc[i - 1]) {
                counter++;
                if(old > 0) {
                    old--;
                    res++;
                }
            } else {
                res++;
                old = --counter;
                counter = 1;
            }
        }
        return res;
    }
}
