package com.home.learn.google;

public class LongestPalindrome {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        char[] c = s.toCharArray();
        int[] max = new int[]{0, 0};
        int i = 0;
        while (i < c.length) {
            i = expand(c, i, max);
        }
        return s.substring(max[1], max[1] + max[0]);
    }

    public int expand(char[] c, int i, int[] max) {
        int left = i - 1, right = ++i;
        while (right < c.length && c[right - 1] == c[right]) right++;
        i = right;

        while (left >= 0 && right < c.length && c[left] == c[right]) {
            left--;
            right++;
        }
        if (right - left - 1 > max[0]) {
            max[0] = right - left - 1;
            max[1] = left + 1;
        }
        return i;
    }


    public String longestPalindromeManacher(String s) {
        char[] T = new char[s.length() * 2 + 1];
        int[] p = new int[T.length];
        int center = 0, maxRight = 0;
        for(int i = 0; i < s.length(); i++){
            T[2 * i + 1] = s.charAt(i);
        }
        p[0] = 1;
        for(int i = 1; i < p.length; i++){
            p[i] = i < maxRight ?  Math.min(p[2 * center - i], maxRight - i + 1) : 1;
            while(i - p[i] >= 0 && i + p[i] < T.length && T[i - p[i]] == T[i + p[i]]) {
                p[i]++;
            }
            if (p[i] > maxRight - center + 1) {
                center = i;
                maxRight = i + p[i] - 1;
            }
        }
        return s.substring((2 * center - maxRight) / 2, maxRight / 2);
    }

    public String longestPalindromeV2(String s) {
        if (s.length() == 0) return "";
        int min = 0, max = 1 ;
        int idx = 1 , length = s.length();
        while (idx < length) {
            int [] even = longestPalindromeHelper(idx - 1, idx, s);
            int [] odd = longestPalindromeHelper(idx -1 , idx + 1, s);
            int [] v = (even[1] - even[0] > odd[1] - odd[0])? even: odd;
            if (max - min < v[1] - v[0]) {
                max = v[1];
                min = v[0];
            }
            idx ++;
        }
        return s.substring(min, max);
    }

    private int [] longestPalindromeHelper(int left, int right, String s) {
        while (left>=0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right ++;
        }
        return new int[]{left + 1, right};
    }
}
