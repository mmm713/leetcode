package com.home.learn.smartnews;

import java.util.Comparator;
import java.util.PriorityQueue;

public class RearrangeStringKDistanceApart {

    public String rearrangeString(String s, int k) {
        if (k == 0) return s;
        int[] m = new int[26];
        for (int i = 0; i < s.length(); i++) {
            m[s.charAt(i) - 'a']++;
        }
        PriorityQueue<Character> pq = new PriorityQueue<>(Comparator.comparing(a -> m[(char)a - 'a']).reversed());
        for (int i = 0; i < 26; i++) {
            if (m[i] != 0) pq.add((char)(i + 'a'));
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            char curr = pq.poll();
            sb.append(curr);
            m[curr - 'a']--;
            if (sb.length() >= k) {
                if (m[sb.charAt(sb.length() - k) - 'a'] > 0) {
                    pq.add(sb.charAt(sb.length() - k));
                }
            }
        }
        return sb.length() != s.length() ? "" : sb.toString();
    }
}
