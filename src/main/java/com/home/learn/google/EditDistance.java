package com.home.learn.google;

public class EditDistance {
    public int minDistance(String word1, String word2) {
        int[][] wdp = new int[word1.length()+1][word2.length()+1];
        for(int i = 0; i < wdp.length; i++) wdp[i][0] = i;
        for(int i = 0; i < wdp[0].length; i++) wdp[0][i] = i;
        for(int i = 1; i < wdp.length; i++) {
            for(int j = 1; j < wdp[0].length; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    wdp[i][j] = wdp[i - 1][j - 1];
                } else {
                    wdp[i][j] = Math.min(Math.min(wdp[i - 1][j], wdp[i][j - 1]), wdp[i - 1][j - 1]) + 1;
                }
            }
        }
        return wdp[word1.length()][word2.length()];
    }

    public boolean isOneEditDistance(String s, String t) {
        for (int i = 0; i < Math.min(s.length(), t.length()); ++i) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) return s.substring(i + 1).equals(t.substring(i + 1));
                if (s.length() < t.length()) return s.substring(i).equals(t.substring(i + 1));
                else return s.substring(i + 1).equals(t.substring(i));
            }
        }
        return Math.abs(s.length() - t.length()) == 1;
    }
}
