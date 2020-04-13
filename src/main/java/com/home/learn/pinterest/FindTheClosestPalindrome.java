package com.home.learn.pinterest;

import java.util.ArrayList;
import java.util.List;

public class FindTheClosestPalindrome {
    public String nearestPalindromic(String n) {
        // edge cases, no
        int len = n.length();
        int i = (len % 2 == 0) ? (len / 2 - 1) : len / 2;
        long left = Long.parseLong(n.substring(0, i + 1));
        // input: n 12345
        List<Long> candidates = new ArrayList<>();
        candidates.add(getPalindrome(left, len % 2 == 0)); // 12321
        candidates.add(getPalindrome(left + 1, len % 2 == 0)); // 12421
        candidates.add(getPalindrome(left - 1, len % 2 == 0)); // 12221
        candidates.add((long) Math.pow(10, len - 1) - 1); // 9999
        candidates.add((long) Math.pow(10, len) + 1); // 100001

        long diff = Long.MAX_VALUE, res = 0, nl = Long.parseLong(n);
        for (long candidate : candidates) {
            if (candidate == nl) continue;
            if (Math.abs(candidate - nl) < diff) {
                diff = Math.abs(candidate - nl);
                res = candidate;
            } else if (Math.abs(candidate - nl) == diff) {
                res = Math.min(res, candidate);
            }
        }
        return String.valueOf(res);
    }

    private long getPalindrome(long left, boolean even) {
        long res = left;
        if (!even) left = left / 10;
        while (left > 0) {
            res = res * 10 + left % 10;
            left /= 10;
        }
        return res;
    }
}
