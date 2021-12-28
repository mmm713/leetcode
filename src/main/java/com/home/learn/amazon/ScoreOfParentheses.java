package com.home.learn.amazon;

public class ScoreOfParentheses {
    public int scoreOfParentheses(String s) {
        char[] sc = s.toCharArray();
        int[] ia = new int[1];
        return dfs(sc, ia, 0);
    }

    private int dfs(char[] sc, int[] ia, int res) {
        while(ia[0] < sc.length) {
            if(sc[ia[0]] == '(') {
                ia[0]++;
                res += dfs(sc, ia, 0);
            } else {
                ia[0]++;
                return res == 0 ? 1 : 2 * res;
            }
        }
        return res;
    }
}
